package com.mk.home_presentation

sealed class HomeEvent {
    data class OnError(val message: String?) : HomeEvent()
}
