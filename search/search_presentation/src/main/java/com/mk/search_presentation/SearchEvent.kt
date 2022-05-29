package com.mk.search_presentation

sealed class SearchEvent {
    data class OnQueryChanged(val query: String) : SearchEvent()
    object OnSearch : SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean) : SearchEvent()
}
