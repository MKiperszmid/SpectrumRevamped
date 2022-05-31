package com.mk.player_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.mk.core_ui.GraySemiWhite
import com.mk.core_ui.LocalDimensions
import com.mk.core_ui.White

@Composable
fun SongDescription(songTitle: String, artistName: String, modifier: Modifier = Modifier) {
    val dimens = LocalDimensions.current
    Column(modifier = modifier) {
        Text(
            text = songTitle, color = White, style = MaterialTheme.typography.body1,
            maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimens.medium))
        Text(
            text = artistName, color = GraySemiWhite, style = MaterialTheme.typography.subtitle2,
            maxLines = 1, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold
        )
    }
}