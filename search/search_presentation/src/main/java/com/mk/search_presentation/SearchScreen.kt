package com.mk.search_presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.mk.search_presentation.components.SearchBar
import com.mk.search_presentation.components.SearchResultList

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val keyboardController = LocalSoftwareKeyboardController.current

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

        SearchResultList(state.results, onSongClick = {

        }, onError = {

        })
    }
}
