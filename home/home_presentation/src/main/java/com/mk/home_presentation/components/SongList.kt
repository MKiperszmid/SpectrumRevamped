package com.mk.home_presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mk.core_ui.LocalDimensions
import com.mk.home_domain.model.Song

@Composable
fun SongList(
    headerName: String,
    songs: List<Song>,
    onClick: (Song) -> Unit,
    onPaginate: () -> Unit
) {
    if (songs.isEmpty()) return
    val dimens = LocalDimensions.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(dimens.medium))
        Text(
            text = headerName,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(dimens.large))
        val state = rememberLazyListState()
        LazyRow(state = state) {
            items(songs) { song ->
                Spacer(modifier = Modifier.width(dimens.small))
                SongItem(song = song, modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                    onClick = { onClick(song) }
                ))
                Spacer(modifier = Modifier.width(dimens.small))
            }
        }
        if (shouldPaginate(state, songs)) {
            onPaginate()
        }
    }
}

private fun shouldPaginate(state: LazyListState, songs: List<Song>): Boolean {
    return state.layoutInfo.visibleItemsInfo.lastOrNull()?.index == songs.size - 3
}
