package com.example.exchangerate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exchangerate.ui.theme.ExchangeRateTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.colorResource
import java.util.Currency
import androidx.compose.foundation.border as border1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeRateTheme {
                RunApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun SplashScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(R.drawable.global_shares),
        contentDescription = null
    )

//    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 160.dp, 16.dp, 48.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
        ) {
            Text(
                "Exchange\nRate",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
        } // end Row
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,

            ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onContinueClicked,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Login",
                    modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                ) // end Text
            }
        } // end Row (Button)
    } // end Column
} // end SplashScreen

@Composable
fun RunApp(modifier: Modifier = Modifier) {
    // Manage the state - remember if if user "logged in",
    // so he is not moved back to the login screen again
    // when state changes, e.g. screen rotated
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            SplashScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            MyScaffold()
        }
    }
} // end RunApp

@Composable
fun LatestConversionRate(modifier: Modifier = Modifier) {
    // Get current date, format it and store in the variable
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = currentDate.format(formatter)

    // Store conversion rates in the list
    val conversionList: List<ConversionRate> = conversionRates

    Column() {
        Column(modifier.padding(16.dp)) {
            Text(
                text = "Date: $formattedDate",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
        } // end Column (Date)

        LazyColumn(modifier = modifier.padding(vertical = 8.dp)) {
            // Conversion rates here (cards?)
            items(items = conversionList) { conversion ->
                Conversion(conversion)
            }
        } // end Column Conversion rates
    } // end first Column
} // end LatestConversion Rate

@Composable
fun Conversion(conRate: ConversionRate, modifier: Modifier = Modifier) {
    val euroCurrency = Currency.getInstance("EUR")
    Surface(
//        color = MaterialTheme.colorScheme.secondary,
    ) {
        Column(
//            modifier.padding(10.dp)
        ) {
            Card(
                modifier
                    .padding(2.dp)
                    .fillMaxWidth(),
                elevation = 4.dp,
                shape = RoundedCornerShape(5.dp),
            ) {
                Row(
                    modifier
                        .padding(16.dp)
                ) {
                    Text(text = "1 ${euroCurrency.symbol} = ${conRate.currencyRate} ${conRate.currencySymbol}")
                }
            }
        }
//        Row(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            Text(text = "1 ${euroCurrency.symbol} = ${conRate.currencyRate} ${conRate.currencySymbol}")
//        }
    }
} // end Conversion Rate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarFun(modifier: Modifier = Modifier) {
    Column {
        TopAppBar(
            title = {
                Text(
                    "Latest conversion rate",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            backgroundColor = MaterialTheme.colorScheme.primary
        )
    }
} // end TopAppBarFun

@Composable
fun BottomAppBar(modifier: Modifier = Modifier) {

    val selectedIndex = remember { mutableStateOf(0) }

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary,
//        modifier = modifier.height(100.dp)
    ) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Circle, "")
        },
            label = {
                Text(
                    text = "Latest rate",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.TrendingUp, "")
        },
            label = {
                Text(
                    text = "Converter",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })
        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Euro, "")
        },
            label = {
                Text(
                    text = "Currencies list",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
    } // end BottomNavigation
} // end BottomAppBarFun

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyScaffold(modifier: Modifier = Modifier) {

    val scaffoldState = rememberScaffoldState()
//    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
//        drawerContent = { Text("Drawer content") },
        topBar = {
            TopAppBarFun()
//            TopAppBar(
//                title = {
//                    Text(
//                        "Latest conversion rate",
//                        style = MaterialTheme.typography.bodyLarge,
//                        color = MaterialTheme.colorScheme.onPrimary
//                    )
//                },
//                backgroundColor = MaterialTheme.colorScheme.primary
//            )// end TopAppBar
        },
        content = {
            LatestConversionRate()
        }, // end content
        bottomBar = {
            BottomAppBar()
        },

        )
}

//@Preview(showBackground = true, widthDp = 320, heightDp = 720)
//@Composable
//fun SplashScreenPreview() {
//    ExchangeRateTheme {
//        SplashScreen(onContinueClicked = {})
//    }
//}

//@Preview
//@Composable
//fun ConversionPreview() {
//    ExchangeRateTheme() {
//        Conversion(ConversionRate("Euro", 1.234))
//    }
//}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun BottomAppBarPreview() {
    ExchangeRateTheme {
        BottomAppBar()
    }
}

@Preview
@Composable
fun MyScaffoldPreview() {
    ExchangeRateTheme {
        MyScaffold()
    }
}

@Preview(showBackground = true, widthDp = 380, heightDp = 720)
@Composable
fun DefaultPreview() {
    ExchangeRateTheme {
        TopAppBarFun()
    }
}