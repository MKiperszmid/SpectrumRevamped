package com.mk.home_presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mk.core_ui.LocalDimensions
import com.mk.home_domain.model.Song
import kotlinx.coroutines.flow.Flow

@Composable
fun SongList(
    headerName: String,
    songsFlow: Flow<PagingData<Song>>,
    onSongClick: (Song) -> Unit
) {
    val songs = songsFlow.collectAsLazyPagingItems()
    if (songs.itemCount == 0) return

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
                song?.let {
                    Spacer(modifier = Modifier.width(dimens.small))
                    SongItem(song = song, modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                        onClick = { onSongClick(song) }
                    ))
                    Spacer(modifier = Modifier.width(dimens.small))
                }
            }
        }
    }
}
