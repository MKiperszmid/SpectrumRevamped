package com.mk.player_presentation

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.mk.player_presentation.service.MusicService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(

) : ViewModel() {

    fun onCommand(context: Context, action: String) {
        Intent(context, MusicService::class.java).also {
            it.action = action
            context.startService(it)
        }
    }
}