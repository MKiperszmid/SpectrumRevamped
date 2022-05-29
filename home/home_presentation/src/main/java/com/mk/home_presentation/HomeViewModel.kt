package com.mk.home_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mk.core_ui.UIEvent
import com.mk.core_ui.UIText
import com.mk.home_domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getTopSongs()
        getPopularArgentina()
    }

    fun onEvent(homeEvent: HomeEvent) {
        when (homeEvent) {
            is HomeEvent.OnError -> {
                viewModelScope.launch {
                    displayError(homeEvent.message)
                }
            }
        }
    }

    private fun getPopularArgentina() {
        state =
            state.copy(popularArgentina = homeUseCases.popularArgentina().cachedIn(viewModelScope))
    }

    private fun getTopSongs() {
        state = state.copy(topSongs = homeUseCases.topSongs().cachedIn(viewModelScope))
    }

    private suspend fun displayError(message: String?) {
        val uiText =
            if (message.isNullOrEmpty()) UIText.StringResource(com.mk.core.R.string.unknown_error)
            else UIText.DynamicString(message)
        _uiEvent.send(UIEvent.ShowSnackbar(uiText))
    }
}