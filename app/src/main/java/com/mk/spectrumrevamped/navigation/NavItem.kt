package com.mk.spectrumrevamped.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    @StringRes val name: Int,
    val route: String,
    val icon: ImageVector
)
