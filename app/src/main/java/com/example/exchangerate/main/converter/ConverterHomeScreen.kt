package com.example.exchangerate.main.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.exchangerate.main.latest.RatesUiState
import com.example.exchangerate.main.latest.makeRatesList
import com.example.exchangerate.model.CurrencyRates
import com.example.exchangerate.model.Rate
import com.example.exchangerate.main.currencies.ErrorScreen
import com.example.exchangerate.main.currencies.LoadingScreen
import java.text.DecimalFormat

var theAmount = 1.0
var theRate = 0.0

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
    var rate by rememberSaveable { mutableStateOf("0.0") }

    theAmount = amount?.toDoubleOrNull() ?: 1.0
    theRate = rate?.toDoubleOrNull() ?: 0.0

    Column(
        modifier.padding(16.dp)
    ) {
        Text(text = "From")
        Row {
            CurrencyMenuBoxFrom(currencyRates = currencyRates, modifier = Modifier)
            OutlinedTextFieldFrom(amount = amount, onAmountChange = { amount = it })
        }
        Text(text = "To")
        Row {
            CurrencyMenuBoxTo(myRatesList = myRatesList, onRateChange = { theRate = rate.toDouble() }, modifier = Modifier)
        }
    }
}

@Composable
fun OutlinedTextFieldFrom(amount: String, onAmountChange: (String) -> Unit) {
    OutlinedTextField(
        value = amount,
        maxLines = 1,
        onValueChange = onAmountChange,
        label = { Text("") },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

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

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a string value to store the selected currency
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
fun CurrencyMenuBoxTo(
    myRatesList: List<Rate>,
    onRateChange: (Double) -> Unit,
    modifier: Modifier
) {
//
    var rate by rememberSaveable { mutableStateOf(myRatesList[0].rate) }

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a string value to store the selected currency
    var mSelectedText by remember { mutableStateOf(myRatesList[0].shortName) }

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
                myRatesList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label.shortName
                        rate = label.rate
                        mExpanded = false
                    }) {
                        Text(text = label.shortName)
                    }
                }
            }
        } // end Column (Dropdown)
        Divider(
            color = Color.White, modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(25.dp)
        )
        onRateChange(rate)
        OutlinedTextFieldTo(amount = theAmount, rate = rate)
    }
} // End Currency Menu Box To