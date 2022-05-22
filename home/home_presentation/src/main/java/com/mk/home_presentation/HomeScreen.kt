package com.mk.home_presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Text(text = stringResource(id = com.mk.core.R.string.recently_added))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}