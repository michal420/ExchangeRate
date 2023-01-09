package com.example.exchangerate

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exchangerate.ui.theme.ExchangeRateTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.exchangerate.screens.Latest
import com.example.exchangerate.screens.Converter
import com.example.exchangerate.screens.Currencies
import java.nio.file.Files.size

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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 160.dp, 16.dp, 48.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { ExchangeRateTopAppBar(navController) },
        content = { NavigationHost(navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController) },
    )
}

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

// Function to get title string of the route
fun getTitleByRoute(context: Context, route: String?): String {
    return when (route) {
        "latest" -> context.getString(R.string.latest_conversion)
        // other cases
        "converter" -> context.getString(R.string.currency_converter)
        else -> context.getString(R.string.currencies_list)
    }
}

@Composable
fun ExchangeRateTopAppBar(navController: NavHostController) {
    val context = LocalContext.current
    var title by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            // You can map the title based on the route using:
            title = getTitleByRoute(context, backStackEntry?.destination?.route)
        }
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        backgroundColor = MaterialTheme.colorScheme.primary
    )
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
                    Text(
                        text = navItem.title,
                        color = if (currentRoute == navItem.route) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.inversePrimary
                        }
                    )
                },
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title,
                        tint = if (currentRoute == navItem.route) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.inversePrimary
                        }
                    )
                },
            ) // end BottomNavigationItem
        }
    }
}

@Preview(showBackground = true, widthDp = 420, heightDp = 720)
@Composable
fun DefaultPreview() {
    ExchangeRateTheme {
        MainScreen()
    }
}