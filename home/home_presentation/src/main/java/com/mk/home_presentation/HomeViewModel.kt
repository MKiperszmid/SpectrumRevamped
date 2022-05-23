package com.mk.home_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mk.core_ui.UIEvent
import com.mk.home_domain.use_case.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
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
    }

    private fun getTopSongs() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            homeUseCases.topSongs().onSuccess {
                state = state.copy(
                    topSongs = it,
                    isLoading = false
                )
            }.onFailure {
                state = state.copy(isLoading = false)
                it.message?.let { error ->
                    _uiEvent.send(UIEvent.ShowSnackbar(error))
                }
            }
        }
    }
}