package com.mk.search_presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mk.core_ui.LocalDimensions
import com.mk.core_ui.components.Loader
import com.mk.seach_domain.model.Song
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchResultList(
    songsFlow: Flow<PagingData<Song>>,
    onSongClick: (Song) -> Unit,
    onError: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val songs = songsFlow.collectAsLazyPagingItems()
    if (songs.itemCount == 0) return

    val dimens = LocalDimensions.current

    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(dimens.medium))
        LazyColumn {
            items(songs) { song ->
                song?.let {
                    Spacer(modifier = Modifier.width(dimens.small))
                    SearchResultItem(song = song, modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { onSongClick(song) }
                    ))
                    Spacer(modifier = Modifier.width(dimens.small))
                }
            }
        }
    }

    songs.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                Loader(shouldLoad = true)
            }
            loadState.append is LoadState.Loading -> {
                Loader(shouldLoad = true)
            }
            loadState.refresh is LoadState.Error -> {
                val e = songs.loadState.refresh as LoadState.Error
                onError(e.error.message)
            }
            loadState.append is LoadState.Error -> {
                val e = songs.loadState.append as LoadState.Error
                onError(e.error.message)
            }
            else -> {}
        }
    }
}