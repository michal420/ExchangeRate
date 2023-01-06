package com.example.exchangerate.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.exchangerate.ui.theme.ExchangeRateTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.exchangerate.ConversionRate
import com.example.exchangerate.conversionRates

val currenciesList = conversionRates as MutableList<ConversionRate>

//var rateFrom = currenciesList[0].currencyRate
//var rateTo = currenciesList[0].currencyRate

@Composable
fun Converter(modifier: Modifier = Modifier) {
    var selectedCurrency by rememberSaveable { mutableStateOf("") }
//    val rate: MutableState<Double> = remember{mutableStateOf(1.0)}
//    var rate by rememberSaveable { mutableStateOf(0.0) }

    Column(
        modifier.padding(16.dp)
    ) {
        Text(text = "From")
        CurrencyMenuBoxFrom( modifier = Modifier)
        Text(text = "To")
        CurrencyMenuBoxTo(modifier = Modifier)
        ConvertButton()
    } // end Column
} // end Converter

@Composable
fun OutlinedTextFieldFrom(rate: Double) {
    OutlinedTextField(
        readOnly = true,
        value = rate.toString(),
        onValueChange = { },
        label = { Text("TextField From") },
    )
}

@Composable
fun OutlinedTextFieldTo(rate: Double) {
    OutlinedTextField(
        readOnly = true,
        value = rate.toString(),
        onValueChange = { },
        label = { Text("TextField To") },
    )
}

// -------CURRENCY MENU BOX  FROM---------------
@Composable
fun CurrencyMenuBoxFrom( modifier: Modifier) {
    var rate by rememberSaveable { mutableStateOf(currenciesList[0].currencyRate) }

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

//    val currenciesList = conversionRates as MutableList<ConversionRate>

    // Create a string value to store the selected currency
//    var mSelectedText by remember { mutableStateOf("") }
    var mSelectedText by remember { mutableStateOf(currenciesList[0].currencySymbol) }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

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
            OutlinedTextField(
                value = mSelectedText,
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
                    Icon(icon, "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )
            // Create a drop-down menu with list of currencies,
            // when clicked, set the Text Field with selected currency
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                currenciesList.sortBy { it.currencySymbol }
                currenciesList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label.currencySymbol
                        rate = label.currencyRate
                        mExpanded = false
                    }) {
                        Text(text = label.currencySymbol)
                    }
                }
            }
        } // end Column (Dropdown)
        Divider(
            color = Color.White,
            modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(25.dp)
        )
        OutlinedTextFieldFrom(rate = rate)
    }

} // End Currency Menu Box To

// -------CURRENCY MENU BOX  TO---------------
@Composable
fun CurrencyMenuBoxTo(modifier: Modifier) {
    var rate by rememberSaveable { mutableStateOf(currenciesList[0].currencyRate) }
    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

//    val currenciesList = conversionRates as MutableList<ConversionRate>

    // Create a string value to store the selected currency
    var mSelectedText by remember { mutableStateOf(currenciesList[0].currencySymbol) }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

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
            OutlinedTextField(
                value = mSelectedText,
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
                    Icon(icon, "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )
            // Create a drop-down menu with list of currencies,
            // when clicked, set the Text Field with selected currency
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                currenciesList.sortBy { it.currencySymbol }
                currenciesList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label.currencySymbol
                        rate = label.currencyRate
                        mExpanded = false
                    }) {
                        Text(text = label.currencySymbol)
                    }
                }
            }
        } // end Column (Dropdown)
        Divider(
            color = Color.White,
            modifier = Modifier
                .fillMaxHeight()  //fill the max height
                .width(25.dp)
        )
        OutlinedTextFieldTo(rate = rate)
    }
} // End Currency Menu Box To

@Composable
fun ConvertButton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = {},
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        )
        {
            Text(
                text = "Convert",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 720, widthDp = 380)
@Composable
fun ConverterPreview() {
    ExchangeRateTheme {
        Converter()
    }
}
