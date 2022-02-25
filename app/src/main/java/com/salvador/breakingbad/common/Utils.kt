package com.salvador.breakingbad.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun currentRoute(navController: NavController): String? {
    val backStackEntry = navController.currentBackStackEntryAsState()
    return backStackEntry.value?.destination?.route
}