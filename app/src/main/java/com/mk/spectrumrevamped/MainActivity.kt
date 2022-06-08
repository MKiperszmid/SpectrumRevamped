package com.mk.spectrumrevamped

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.mk.core.R
import com.mk.home_presentation.HomeScreen
import com.mk.player_domain.model.Song
import com.mk.player_presentation.PlayerScreen
import com.mk.player_presentation.model.TrackList
import com.mk.player_presentation.service.MusicService
import com.mk.player_presentation.utils.Constants
import com.mk.search_presentation.SearchScreen
import com.mk.spectrumrevamped.mappers.toPlayer
import com.mk.spectrumrevamped.navigation.BottomNavigationBar
import com.mk.spectrumrevamped.navigation.NavItem
import com.mk.spectrumrevamped.navigation.Route
import com.mk.spectrumrevamped.navigation.RouteParam
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
                        val backstack = navController.currentBackStackEntryAsState()
                        if (Route.PLAYER != backstack.value?.destination?.route) {
                            BottomNavigationBar(
                                items = items,
                                navController = navController,
                                onClick = {
                                    navController.navigate(it.route)
                                })
                        }
                    }) {
                    // Fixes Bottom Navigation Padding
                    Box(modifier = Modifier.padding(it)) {
                        navigationScreens(navController, scaffoldState)
                    }
                }
            }
        }
    }

    @Composable
    private fun navigationScreens(
        navController: NavHostController,
        scaffoldState: ScaffoldState
    ) {
        //ViewModelStoreOwner fixes going to search then back to home, and calling INIT again
        val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
            "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
        }
        NavHost(
            navController = navController,
            startDestination = Route.HOME
        ) {
            composable(Route.HOME) {
                HomeScreen(
                    onSongClick = { song, list ->
                        val tracks = TrackList(list.map { it.toPlayer() })
                        loadSongs(song.toPlayer(), tracks)
                        navController.navigate(Route.PLAYER)
                    },
                    scaffoldState = scaffoldState,
                    viewModel = hiltViewModel(viewModelStoreOwner = viewModelStoreOwner)
                )
            }
            composable(Route.SEARCH) {
                SearchScreen(
                    onSongClick = {
                        loadSongs(it.toPlayer())
                        navController.navigate(Route.PLAYER)
                    },
                    scaffoldState = scaffoldState,
                    viewModel = hiltViewModel(viewModelStoreOwner = viewModelStoreOwner)
                )
            }
            composable(
                route = Route.PLAYER,
                deepLinks = listOf(navDeepLink {
                    uriPattern = "https://www.spectrumrevamped.com/player"
                })
            ) {
                PlayerScreen(
                    onMinimizeClick = { navController.navigateUp() }
                )
            }
            //TODO: Complete with remaining Routes
        }
    }

    private fun loadSongs(song: Song, tracks: TrackList = TrackList()) {
        Intent(this, MusicService::class.java).also {
            it.action = Constants.ACTION_LOAD_SONGS
            val bundle = Bundle().apply {
                putParcelable(Constants.SONG_KEY, song)
                putParcelable(Constants.TRACKLIST_KEY, tracks)
            }
            it.putExtras(bundle)
            startService(it)
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

    override fun onDestroy() {
        val intent = Intent(this, MusicService::class.java)
        stopService(intent)
        super.onDestroy()
    }
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