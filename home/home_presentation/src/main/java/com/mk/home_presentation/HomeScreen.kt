package com.mk.home_presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mk.core.R
import com.mk.core_ui.UIEvent
import com.mk.core_ui.components.Loader
import com.mk.home_domain.model.Song
import com.mk.home_presentation.components.SongList

@Composable
fun HomeScreen(
    onSongClick: (Song) -> Unit,
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            if (it is UIEvent.ShowSnackbar) {
                scaffoldState.snackbarHostState.showSnackbar(it.message.asString(context))
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        SongList(
            headerName = stringResource(id = R.string.top_songs),
            songsFlow = state.topSongs,
            onSongClick = onSongClick
        )
        SongList(
            headerName = stringResource(id = R.string.popular_argentina),
            songsFlow = state.popularArgentina,
            onSongClick = onSongClick
        )
    }
    Loader(state.isLoading)
}
