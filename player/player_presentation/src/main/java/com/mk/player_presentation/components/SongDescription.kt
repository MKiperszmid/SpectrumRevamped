package com.mk.player_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.mk.core_ui.GraySemiWhite
import com.mk.core_ui.LocalDimensions
import com.mk.core_ui.White

@Composable
fun SongDescription(songTitle: String, artistName: String, modifier: Modifier = Modifier) {
    val dimens = LocalDimensions.current
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = songTitle, color = White, style = MaterialTheme.typography.body1,
            maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimens.small))
        Text(
            text = artistName, color = GraySemiWhite, style = MaterialTheme.typography.subtitle2,
            maxLines = 1, overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun PreviewDescription() {
    SongDescription(songTitle = "Tacones Rojos", artistName = "Sebastián Yatra")
}