package com.mk.home_presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mk.core_ui.Gray
import com.mk.core_ui.LocalDimensions
import com.mk.core_ui.White
import com.mk.home_domain.model.Artist
import com.mk.home_domain.model.Song

@Composable
fun SongItem(
    song: Song,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimens = LocalDimensions.current
    Column(
        modifier = modifier
            .width(125.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
    ) {
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

@Preview
@Composable
fun PreviewSongItem() {
    SongItem(
        song = Song(
            1, "This is a very long song name", "", 1, Artist(
                1,
                "This is a very long artist name",
                ""
            ),
            ""
        ), onClick = { }
    )
}
