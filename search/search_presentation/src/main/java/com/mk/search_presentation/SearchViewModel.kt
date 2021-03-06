package com.mk.search_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mk.core_ui.UIEvent
import com.mk.core_ui.UIText
import com.mk.seach_domain.use_case.SearchSong
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchSong: SearchSong
) : ViewModel() {
    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(searchEvent: SearchEvent) {
        when (searchEvent) {
            is SearchEvent.OnQueryChanged -> {
                state = state.copy(
                    query = searchEvent.query
                )
            }
            SearchEvent.OnSearch -> {
                performSearch()
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !searchEvent.isFocused && state.query.isBlank()
                )
            }
            is SearchEvent.OnError -> {
                viewModelScope.launch {
                    displayError(searchEvent.message)
                }
            }
        }
    }

    private suspend fun displayError(message: String?) {
        val uiText =
            if (message.isNullOrEmpty()) UIText.StringResource(com.mk.core.R.string.unknown_error)
            else UIText.DynamicString(message)
        _uiEvent.send(UIEvent.ShowSnackbar(uiText))
    }

    private fun performSearch() {
        viewModelScope.launch {
            state = state.copy(
                results = searchSong(state.query).cachedIn(viewModelScope)
            )
        }
    }
}