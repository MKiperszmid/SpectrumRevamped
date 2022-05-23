package com.mk.home_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mk.core_ui.Gray
import com.mk.core_ui.LocalDimensions
import com.mk.core_ui.White
import com.mk.home_domain.model.Song

@Composable
fun SongItem(
    song: Song,
    modifier: Modifier
) {
    val dimens = LocalDimensions.current
    Column(modifier = modifier.width(125.dp)) {
        AsyncImage(
            model = song.image,
            contentDescription = song.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(125.dp)
        )
        Spacer(modifier = Modifier.height(dimens.extraSmall))
        Text(
            text = song.title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = White,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Text(
            text = song.artist.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            color = Gray,
            fontSize = 10.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}
