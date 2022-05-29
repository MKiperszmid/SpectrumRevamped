package com.mk.search_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mk.seach_domain.use_case.SearchSong
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchSong: SearchSong
) : ViewModel() {
    var state by mutableStateOf(SearchState())
        private set

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
        }
    }

    private fun performSearch() {
        viewModelScope.launch {
            state = state.copy(
                results = searchSong(state.query)
            )
        }
    }
}