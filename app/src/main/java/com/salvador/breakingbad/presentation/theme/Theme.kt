package com.salvador.breakingbad.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    background =  dark_primary_dark,
    primary =  dark_primary,
    primaryVariant =  dark_primary_text,
    secondary =  dark_primary_light,
    secondaryVariant = Color.White,
    surface = dark_accent
)

private val LightColorPalette = lightColors(
    background = Color.White,
    primary = primary,
    primaryVariant = primary_text,
    secondary = primary_light,
    secondaryVariant = Color.Black,
    surface = accent
)

@Composable
fun BreakingBadTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}