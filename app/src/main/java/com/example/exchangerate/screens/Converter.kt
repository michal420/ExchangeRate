package com.example.exchangerate.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun Converter(modifier: Modifier = Modifier) {

    Column(
        modifier.padding(16.dp)
    ) {

        Row() {
            Text(text = "From")
        }
        Row(
            modifier.padding(bottom = 24.dp)
        ) {
            CurrencyMenuBox(modifier = Modifier.padding(end = 8.dp))
            OutlinedTextFieldFrom()
        }

        Row() {
            Text(text = "To")
        }
        Row(modifier = Modifier.padding(bottom = 16.dp)) {
            CurrencyMenuBox(modifier = Modifier.padding(end = 8.dp))
            OutlinedTextFieldFrom()
        }


//        Column {
//            Text(text = "From")
//            Row {
//                Column(
//                    modifier.
//                    weight(1f)
//                ) {
//                    DropdownMenu()
//                }
//                Column(
//                    modifier
//                        .weight(2f)
//                ) {
//                    OutlinedTextFieldFrom()
//                }
//            }
//        } // end Column From

//        Column {
//            Text(text = "To")
//            Row {
//                Column(
//                    modifier.
//                    weight(1f)
//                ) {
//                    DropdownMenu()
//                }
//                Column(
//                    modifier
//                        .weight(2f)
//                ) {
//                    OutlinedTextFieldFrom()
//                }
//            }
//        } // end Column To

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConvertButton()
        }

    } // end Column
} // end Converter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownMenu(modifier: Modifier) {
//    val contextForToast = LocalContext.current.applicationContext
    val listItems = arrayOf("EUR", "CAD", "USD", "GBP", "AUD")

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    // the box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        // text field
        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
//            label = { Text(text = "Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
} // end DropdownMenu

@Composable
fun OutlinedTextFieldFrom() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { },
        onValueChange = {
            text = it
        }
    )
}

// -------CURRENCY MENU BOX  ---------------

@Composable
fun CurrencyMenuBox(modifier: Modifier) {
    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    val currenciesList = listOf("EUR", "CAD", "USD", "GBP", "AUD")

    // Create a string value to store the selected currency
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

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
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            currenciesList.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}
// END CURRENCY MENU BOX MENU BOX ---------------

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

//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun DropdownMenu2() {
//
//    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
//    var expanded by remember { mutableStateOf(false) }
//    var selectedOptionText by remember { mutableStateOf(options[0]) }
//
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = {
//            expanded = !expanded
//        }
//    ) {
//        TextField(
//            readOnly = true,
//            value = selectedOptionText,
//            onValueChange = { },
////            label = { Text("Label") },
//            trailingIcon = {
//                ExposedDropdownMenuDefaults.TrailingIcon(
//                    expanded = expanded
//                )
//            },
//            colors = ExposedDropdownMenuDefaults.textFieldColors()
//        )
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = {
//                expanded = false
//            }
//        ) {
//            options.forEach { selectionOption ->
//                DropdownMenuItem(
//                    onClick = {
//                        selectedOptionText = selectionOption
//                        expanded = false
//                    }
//                ) {
//                    Text(text = selectionOption)
//                }
//            }
//        }
//    }
//} // end DropDownMenu2

//@Composable
//fun DropdownDemo() {
//    var expanded by remember { mutableStateOf(false) }
//    val items = listOf("EUR", "USD", "AUD", "CAD", "GBP", "YEN")
//    val disabledValue = ""
//    var selectedIndex by remember { mutableStateOf(0) }
//    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
//        Text(items[selectedIndex],modifier = Modifier.clickable(onClick = { expanded = true }).background(
//            Color.Gray))
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
////            modifier = Modifier.fillMaxWidth().background(
////                Color.Red)
//        ) {
//            items.forEachIndexed { index, s ->
//                DropdownMenuItem(onClick = {
//                    selectedIndex = index
//                    expanded = false
//                }) {
//                    val disabledText = if (s == disabledValue) {
//                        " (Disabled)"
//                    } else {
//                        ""
//                    }
//                    Text(text = s + disabledText)
//                }
//            }
//        }
//    }
//}

@Preview(showBackground = true, heightDp = 720, widthDp = 380)
@Composable
fun ConverterPreview() {
    ExchangeRateTheme {
        Converter()
    }
}
