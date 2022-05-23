package com.mk.core_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mk.core_ui.Accent

@Composable
fun Loader(
    shouldLoad: Boolean,
    alignment: Alignment = Alignment.BottomCenter
) {
    if (shouldLoad) {
        //.9f due to BottomNavigationBar. Investigate if there's a better way
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(.9f), contentAlignment = alignment) {
            CircularProgressIndicator(color = Accent)
        }
    }
}