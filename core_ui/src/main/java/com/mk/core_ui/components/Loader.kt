package com.mk.core_ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mk.core_ui.Accent
import com.mk.core_ui.LocalDimensions

@Composable
fun Loader(
    shouldLoad: Boolean,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.BottomCenter
) {
    if (shouldLoad) {
        val dimens = LocalDimensions.current
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = dimens.medium),
            contentAlignment = alignment
        ) {
            CircularProgressIndicator(color = Accent)
        }
    }
}