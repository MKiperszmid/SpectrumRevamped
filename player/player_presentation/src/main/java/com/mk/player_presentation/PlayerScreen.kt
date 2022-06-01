package com.mk.player_presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mk.core_ui.LocalDimensions
import com.mk.player_domain.model.Artist
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
//TODO: Receive Song and TrackList over param?
) {
    val dimens = LocalDimensions.current
    val context = LocalContext.current
    val song = defaultSong
    val tracks = defaultTracklist

    LaunchedEffect(key1 = song) {
        onCommand(context, ACTION_LOAD_SONGS, song, tracks)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = song.image,
            contentDescription = song.title,
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
                model = song.image,
                contentDescription = song.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(300.dp)
            )
            SongDescription(
                song.title, song.artist.name
            )
            Spacer(modifier = Modifier.height(dimens.small))
            PlayerSlider(
                duration = song.duration,
                currentValue = 50f,
                onValueChange = {},
                modifier = Modifier.padding(dimens.small)
            )
            Spacer(modifier = Modifier.height(dimens.large))
            PlayerController(isPlaying = MusicService.state.isPlaying,
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

private val defaultSong = Song(
    title = "Tacones Rojos",
    preview = "https://cdns-preview-b.dzcdn.net/stream/c-b4c5609f35dd02d6f1761a9d4f65351c-4.mp3",
    duration = 189,
    artist = Artist(
        id = 1522667852,
        name = "Sebastián Yatra"
    ),
    image = "https://e-cdns-images.dzcdn.net/images/cover/00c297f61a9a7af15833daf8ec87cc8c/500x500-000000-80-0-0.jpg"
)

private val defaultTracklist = TrackList(
    listOf(
        Song(
            title = "Como las Olas y el Mar",
            preview = "https://cdns-preview-b.dzcdn.net/stream/c-bce8ac66ec0542f3189418ebf9e4feef-4.mp3",
            duration = 224,
            artist = Artist(
                id = 9211576,
                name = "El Vega Life"
            ),
            image = "https://e-cdns-images.dzcdn.net/images/cover/89ddd49a5a1de01c7d363d8f784eba08/500x500-000000-80-0-0.jpg"
        ), defaultSong, Song(
            title = "Hawaiian Roller Coaster Ride (From \"Lilo & Stitch\")",
            preview = "https://cdns-preview-3.dzcdn.net/stream/c-350f957583f162854ecfbc5b1cb3b89d-8.mp3",
            duration = 208,
            artist = Artist(
                id = 3950,
                name = "Mark Keali'i Ho'omalu"
            ),
            image = "https://e-cdns-images.dzcdn.net/images/cover/a75e57040c3444afc73542bc04fc37a1/500x500-000000-80-0-0.jpg"
        )
    )
)


@Preview(showBackground = false)
@Composable
fun PreviewPlayerScreen() {
    PlayerScreen(onMinimizeClick = {})
}