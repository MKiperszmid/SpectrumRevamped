package com.mk.home_presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Text(text = "Home Screen!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen()
}