package com.salvador.breakingbad.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.salvador.breakingbad.common.currentRoute
import com.salvador.breakingbad.domain.model.BottomNavItem
import com.salvador.breakingbad.presentation.components.BottomNavigationBar
import com.salvador.breakingbad.presentation.detail.DetailView
import com.salvador.breakingbad.presentation.theme.BreakingBadTheme
import com.salvador.breakingbad.presentation.home.HomeView
import com.salvador.breakingbad.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BreakingBadTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {
                            if(currentRoute(navController)?.startsWith(Screen.DetailScreen.route) != true) {
                                TopAppBar(
                                    title = {
                                        Text(text = "Breaking Bad")
                                    },
                                    backgroundColor = MaterialTheme.colors.primary,
                                    contentColor = Color.White,
                                    elevation = 12.dp)
                            }else{
                                TopAppBar(
                                    title = {
                                        Text(text = "Character Details")
                                    },
                                    backgroundColor = MaterialTheme.colors.primary,
                                    navigationIcon =
                                    {
                                        IconButton(onClick = {
                                            navController.navigateUp()
                                        }) {
                                            Icon(Icons.Rounded.ArrowBack, "Go back")
                                        }
                                    },
                                    contentColor = Color.White,
                                    elevation = 12.dp
                                )
                            }

                        },
                        bottomBar = {
                            if(currentRoute(navController)?.startsWith(Screen.DetailScreen.route) != true){
                                BottomNavigationBar(
                                    items = listOf(
                                        BottomNavItem(
                                            name = "Home",
                                            route = Screen.HomeScreen.route,
                                            icon = Icons.Default.Home
                                        ),
                                        BottomNavItem(
                                            name = "Favorites",
                                            route =  Screen.FavoriteScreen.route,
                                            icon = Icons.Default.Favorite
                                        ),
                                    ),
                                    navController = navController,
                                    onItemClick = { item ->
                                        navController.navigate(item.route)
                                    }
                                )
                            }
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.HomeScreen.route
                        ) {
                            composable(
                                route = Screen.HomeScreen.route
                            ) {
                                HomeView(navController)
                            }
                            composable(
                                route = Screen.FavoriteScreen.route
                            ) {
                                HomeView(navController, showFavorites = true)
                            }
                            composable(
                                route = Screen.DetailScreen.route + "/{characterId}"
                            ) {
                                DetailView()
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BreakingBadTheme {}
}