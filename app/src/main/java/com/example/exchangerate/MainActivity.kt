package com.example.exchangerate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exchangerate.ui.theme.ExchangeRateTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.exchangerate.screens.Latest
import com.example.exchangerate.screens.Converter
import com.example.exchangerate.screens.Currencies

import java.util.Currency

enum class ExchangeRateScreen(@StringRes val title: Int) {
    Latest(title = R.string.latest_conversion),
    Converter(title = R.string.currency_converter),
    Currencies(title = R.string.currencies_list)
}

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
fun RunApp(modifier: Modifier = Modifier) {
    // Manage the state - remember if if user "logged in",
    // so he is not moved back to the login screen again
    // when state changes, e.g. screen rotated
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            SplashScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            MainScreen()
        }
    }
} // end RunApp

@Composable
fun SplashScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val image = painterResource(R.drawable.global_shares)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentScale = ContentScale.Crop
    )

    // Navigation Controller
    val navController = rememberNavController()

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

//@Composable
//fun Conversion(conRate: ConversionRate, modifier: Modifier = Modifier) {
//    val euroCurrency = Currency.getInstance("EUR")
//    Surface(
////        color = MaterialTheme.colorScheme.secondary,
//    ) {
//        Column(
////            modifier.padding(10.dp)
//        ) {
//            Card(
//                modifier
//                    .padding(2.dp)
//                    .fillMaxWidth(),
//                elevation = 4.dp,
//                shape = RoundedCornerShape(5.dp),
//            ) {
//                Row(
//                    modifier
//                        .padding(16.dp)
//                ) {
//                    Text(text = "1 ${euroCurrency.symbol} = ${conRate.currencyRate} ${conRate.currencySymbol}")
//                }
//            }
//        }
//    }
//} // end Conversion Rate

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Latest.route,
    ) {
        composable(NavRoutes.Latest.route) {
            Latest()
        }
        composable(NavRoutes.Converter.route) {
            Converter()
        }
        composable(NavRoutes.Currencies.route) {
            Currencies()
        }
    }
} // end NavigationHost

@Composable
fun ExchangeRateTopAppBar(
    modifier: Modifier = Modifier
) {
    NavBarItems.BarItems.forEach { navItem ->
        TopAppBar(
            title = {
                Text(
                    text = navItem.title,
//                    text = stringResource(currentScreenTitle),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            backgroundColor = MaterialTheme.colorScheme.primary
        )
    }
}// end ExchangeRateTopAppBar

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary,
    ) {
        // Identify the route of the currently selected nav
        // destination - obtain current back stack entry to
        // access the route
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        // Iterate through the items from BarItems and use
        // their fields
        NavBarItems.BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(text = navItem.title, color = Color.White)
                },
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title,
                        tint = Color.White
                    )
                },
            ) // end BottomNavigationItem
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val scaffoldState = rememberScaffoldState()

    // Variable for Navigation State
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
//    val currentScreen = ExchangeRateScreen.valueOf(
//        backStackEntry?.destination?.route ?: ExchangeRateScreen.Latest.name
//    )

//    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { ExchangeRateTopAppBar() },
        content = { NavigationHost(navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController) },
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

//@Preview(showBackground = true, widthDp = 400)
//@Composable
//fun BottomAppBarPreview() {
//    ExchangeRateTheme {
//        BottomNavigationBar()
//    }
//}

//@Preview
//@Composable
//fun MyScaffoldPreview() {
//    ExchangeRateTheme {
//        MyScaffold()
//    }
//}

@Preview(showBackground = true, widthDp = 380, heightDp = 720)
@Composable
fun DefaultPreview() {
    ExchangeRateTheme {
        MainScreen()
    }
}