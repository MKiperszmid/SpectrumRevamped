package com.mk.player_presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mk.core.R
import com.mk.core_ui.GrayWhite
import com.mk.core_ui.LocalDimensions
import com.mk.player_domain.model.Song
import com.mk.player_presentation.components.PlayerController
import com.mk.player_presentation.components.PlayerSlider
import com.mk.player_presentation.components.SongDescription
import com.mk.player_presentation.model.TrackList
import com.mk.player_presentation.service.MusicService
import com.mk.player_presentation.utils.Constants.ACTION_LOAD_SONGS
import com.mk.player_presentation.utils.Constants.ACTION_NEXT
import com.mk.player_presentation.utils.Constants.ACTION_PLAY_OR_PAUSE
import com.mk.player_presentation.utils.Constants.ACTION_PREVIOUS
import com.mk.player_presentation.utils.Constants.SONG_KEY
import com.mk.player_presentation.utils.Constants.TRACKLIST_KEY

@Composable
fun PlayerScreen(
    onMinimizeClick: () -> Unit
) {
    val dimens = LocalDimensions.current
    val context = LocalContext.current
    val musicServiceState = MusicService.state

    val currentSong = musicServiceState.currentSong

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = onMinimizeClick, modifier = Modifier.align(Alignment.TopStart).padding(dimens.small)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = GrayWhite,
                contentDescription = stringResource(id = R.string.minimize)
            )
        }
        AsyncImage(
            model = currentSong.image,
            contentDescription = currentSong.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = currentSong.image,
                contentDescription = currentSong.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier = Modifier.height(dimens.small))
            SongDescription(
                currentSong.title, currentSong.artist.name
            )
            Spacer(modifier = Modifier.height(dimens.small))
            PlayerSlider(
                duration = currentSong.duration,
                currentValue = musicServiceState.currentSeconds.toFloat(),
                onValueChange = {},
                modifier = Modifier.padding(start = dimens.medium, end = dimens.medium)
            )
            Spacer(modifier = Modifier.height(dimens.large))
            PlayerController(isPlaying = musicServiceState.isPlaying,
                onPrevious = {
                    onCommand(context, ACTION_PREVIOUS)
                },
                onPlayPause = {
                    onCommand(context, ACTION_PLAY_OR_PAUSE)
                },
                onNext = {
                    onCommand(context, ACTION_NEXT)
                })
        }
    }
}

private fun onCommand(
    context: Context,
    action: String,
    song: Song? = null,
    tracks: TrackList = TrackList()
) {
    Intent(context, MusicService::class.java).also {
        it.action = action
        val bundle = Bundle()
        if (song != null) {
            bundle.putParcelable(SONG_KEY, song)
            bundle.putParcelable(TRACKLIST_KEY, tracks)
            it.putExtras(bundle)
        }
        context.startService(it)
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewPlayerScreen() {
    PlayerScreen(onMinimizeClick = {})
}