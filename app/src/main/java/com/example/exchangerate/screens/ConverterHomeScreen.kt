package com.example.exchangerate.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.exchangerate.model.CurrencyRates
import com.example.exchangerate.model.MyRate
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

var theAmount = 1.0

@Composable
fun ConverterHomeScreen(
    ratesUiState: RatesUiState, retryAction: () -> Unit, modifier: Modifier = Modifier
) {
    when (ratesUiState) {
        is RatesUiState.Loading -> LoadingScreen(modifier)
        is RatesUiState.Success -> ConverterScreen(ratesUiState.rates, modifier)
        is RatesUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun ConverterScreen(currencyRates: CurrencyRates, modifier: Modifier = Modifier) {

    val myRatesList = makeRatesList(currencyRates.rates)

    var amount by rememberSaveable { mutableStateOf("1.0") }
    theAmount = amount.toDouble()
    Log.d("amt", "amount: $amount")

    Column(
        modifier.padding(16.dp)
    ) {
        Text(text = "From")
        Row() {
            CurrencyMenuBoxFrom(currencyRates = currencyRates, modifier = Modifier)
            OutlinedTextFieldFrom(amount = amount, onAmountChange = { amount = it })
        }
        Text(text = "To")
        Row() {
            CurrencyMenuBoxTo(myRatesList = myRatesList, modifier = Modifier)
        }
    }
}

@Composable
fun OutlinedTextFieldFrom(amount: String, onAmountChange: (String) -> Unit) {
//    val maxLength = 6
    OutlinedTextField(
        value = "$amount",
        maxLines = 1,
        onValueChange = onAmountChange,
//        onValueChange = { manageLength(it) },
//        onValueChange = {
//            if (amount.length <= maxLength) onAmountChange
//        },
        label = { Text("") },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

// Prevent the user from entering too many and too little characters
//private fun manageLength(input: String) = if (input.length > 6)
//    input.substring(0..5)
//else input

@Composable
fun OutlinedTextFieldTo(amount: Double, rate: Double) {
    val df = DecimalFormat("#.####")
    OutlinedTextField(
        readOnly = true,
        value = "${df.format(amount * rate)}",
        maxLines = 1,
        onValueChange = { },
        label = { Text("") },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
    )
}

// -------CURRENCY MENU BOX  FROM---------------
@Composable
fun CurrencyMenuBoxFrom(currencyRates: CurrencyRates, modifier: Modifier) {

    // Get a Euro currency for the dropdown
    val euroList = listOf(currencyRates.base)

    // Return currency symbol and rates as the list of MyRates
//    val myRatesList = makeRatesList(currencyRates.rates)

//    var rate by rememberSaveable { mutableStateOf(myRatesList[0].rate) }

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a string value to store the selected currency
//    var mSelectedText by remember { mutableStateOf("") }
    var mSelectedText by remember { mutableStateOf(euroList[0]) }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

    Row(
        modifier
            .padding(bottom = 24.dp)
            .height(IntrinsicSize.Min)
    ) {
        Column(
//        Modifier.padding(20.dp)
        ) {

            // Create an Outlined Text Field
            // with icon and not expanded
            OutlinedTextField(value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
//                .fillMaxWidth()
                    .width(100.dp)
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign the same width
                        //  to the DropDown
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = { Text("") },
                readOnly = true,
                trailingIcon = {
                    Icon(icon, "contentDescription", Modifier.clickable { mExpanded = !mExpanded })
                })
            // Create a drop-down menu with list of currencies,
            // when clicked, set the Text Field with selected currency
            DropdownMenu(
                expanded = false,
//                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
//                currenciesList.sortBy { it.currencySymbol }
                euroList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
        } // end Column (Dropdown)
        Divider(
            color = Color.White, modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(25.dp)
        )
    }

} // End Currency Menu Box To

// -------CURRENCY MENU BOX  TO---------------
@Composable
fun CurrencyMenuBoxTo(myRatesList: List<MyRate>, modifier: Modifier) {
//    // Return currency symbol and rates as the list of MyRates
//    val myRatesList = makeRatesList(currencyRates.rates)
//
    var rate by rememberSaveable { mutableStateOf(myRatesList[0].rate) }
    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a string value to store the selected currency
    var mSelectedText by remember { mutableStateOf(myRatesList[0].symbol) }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

    Row(
        modifier
            .padding(bottom = 24.dp)
            .height(IntrinsicSize.Min)
    ) {
        Column(
//        Modifier.padding(20.dp)
        ) {

            // Create an Outlined Text Field
            // with icon and not expanded
            OutlinedTextField(value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
//                .fillMaxWidth()
                    .width(100.dp)
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign the same width
                        //  to the DropDown
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = { Text("") },
                readOnly = true,
                trailingIcon = {
                    Icon(icon, "contentDescription", Modifier.clickable { mExpanded = !mExpanded })
                })
            // Create a drop-down menu with list of currencies,
            // when clicked, set the Text Field with selected currency
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
//                currenciesList.sortBy { it.currencySymbol }
                myRatesList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label.symbol
                        rate = label.rate
                        mExpanded = false
                    }) {
                        Text(text = label.symbol)
                    }
                }
            }
        } // end Column (Dropdown)
        Divider(
            color = Color.White, modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(25.dp)
        )
        OutlinedTextFieldTo(amount = theAmount, rate = rate)
    }
} // End Currency Menu Box To

@Composable
fun ConvertCurrency(amountX: String, currencyRates: CurrencyRates) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
    ) {
        Text(
            text = "Convert", modifier = Modifier.padding(8.dp)
        )
    }
}
