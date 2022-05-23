package com.mk.spectrumrevamped

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mk.core.R
import com.mk.spectrumrevamped.navigation.Route
import com.mk.home_presentation.HomeScreen
import com.mk.spectrumrevamped.navigation.BottomNavigationBar
import com.mk.spectrumrevamped.navigation.NavItem
import com.mk.spectrumrevamped.ui.theme.SpectrumRevampedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpectrumRevampedTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val items = navigationItems()
                Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState,
                    bottomBar = {
                        BottomNavigationBar(
                            items = items,
                            navController = navController,
                            onClick = {
                                navController.navigate(it.route)
                            })
                    }) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.HOME
                    ) {
                        composable(Route.HOME) {
                            HomeScreen({
                                //TODO: Complete with onClick
                            })
                        }
                        //TODO: Complete with remaining Routes
                    }
                }
            }
        }
    }

    @Composable
    private fun navigationItems() = listOf(
        NavItem(
            R.string.home,
            Route.HOME,
            Icons.Default.Home
        ),
        NavItem(
            R.string.explore,
            Route.EXPLORE,
            Icons.Default.Explore
        ),
        NavItem(
            R.string.search,
            Route.SEARCH,
            Icons.Default.Search
        ),
        NavItem(
            R.string.profile,
            Route.PROFILE,
            Icons.Default.Person
        )
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpectrumRevampedTheme {
        Greeting("Android")
    }
}