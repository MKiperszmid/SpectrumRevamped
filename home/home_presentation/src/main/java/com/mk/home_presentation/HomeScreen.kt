package com.mk.home_presentation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mk.core.R
import com.mk.core_ui.UIEvent
import com.mk.home_domain.model.Song
import com.mk.home_presentation.components.SongList

@Composable
fun HomeScreen(
    onClick: (Song) -> Unit,
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
    SongList(
        headerName = stringResource(id = R.string.top_songs),
        songs = state.topSongs,
        onClick = onClick
    )
}
