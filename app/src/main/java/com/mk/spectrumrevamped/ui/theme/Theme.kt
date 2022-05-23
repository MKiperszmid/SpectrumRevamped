package com.mk.spectrumrevamped.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.mk.core_ui.Accent
import com.mk.core_ui.Primary
import com.mk.core_ui.PrimaryDark

@SuppressLint("ConflictingOnColor")
private val ColorPalette = lightColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    secondary = Accent,
    background = PrimaryDark


    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SpectrumRevampedTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}