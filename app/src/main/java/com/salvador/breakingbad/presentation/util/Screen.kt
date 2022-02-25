package com.salvador.breakingbad.presentation.util

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object FavoriteScreen: Screen("favorite_screen")
    object DetailScreen: Screen("detail_screen")
}