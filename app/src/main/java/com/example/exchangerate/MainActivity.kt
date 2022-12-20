package com.example.exchangerate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.launch

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

//    var shouldShowOnboarding by remember { mutableStateOf(true) }

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
    var shouldShowOnboarding by remember { mutableStateOf(true) }

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
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = currentDate.format(formatter)
//    Log.d("tag", "Current date is $formattedDate")

    Column(modifier.padding(16.dp)) {
        Text(
            text = "Date: $formattedDate",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
        )
    } // end Column (Date)

    Column() {
        // Conversion rates here (cards?)
    } // end Column Conversion rates
} // end LatestConversion Rate

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
} // end TopAppBar

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
        } // end content

    )
}

//@Preview(showBackground = true, widthDp = 320, heightDp = 720)
//@Composable
//fun SplashScreenPreview() {
//    ExchangeRateTheme {
//        SplashScreen(onContinueClicked = {})
//    }
//}

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