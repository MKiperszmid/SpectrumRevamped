package com.mk.search_presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.mk.core_ui.UIEvent
import com.mk.seach_domain.model.Song
import com.mk.search_presentation.components.SearchBar
import com.mk.search_presentation.components.SearchResultList

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    onSongClick: (Song) -> Unit,
    scaffoldState: ScaffoldState,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            if (it is UIEvent.ShowSnackbar) {
                scaffoldState.snackbarHostState.showSnackbar(it.message.asString(context))
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(value = state.query,
            onValueChange = {
                viewModel.onEvent(SearchEvent.OnQueryChanged(it))
            },
            shouldShowHint = state.isHintVisible,
            performSearch = {
                keyboardController?.hide()
                viewModel.onEvent(SearchEvent.OnSearch)
            }, onFocusChange = {
                viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
            })

        SearchResultList(state.results, onSongClick = onSongClick, onError = {
            viewModel.onEvent(SearchEvent.OnError(it))
        })
    }
}
