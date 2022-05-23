package com.mk.core_ui

sealed class UIEvent {
    data class ShowSnackbar(val message: UIText) : UIEvent()
}
