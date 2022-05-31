package com.mk.player_presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mk.core_ui.LocalDimensions
import com.mk.player_domain.model.Artist
import com.mk.player_domain.model.Song
import com.mk.player_presentation.components.PlayerController
import com.mk.player_presentation.components.PlayerSlider
import com.mk.player_presentation.components.SongDescription
import com.mk.player_presentation.utils.Constants.ACTION_NEXT
import com.mk.player_presentation.utils.Constants.ACTION_PLAY_OR_PAUSE
import com.mk.player_presentation.utils.Constants.ACTION_PREVIOUS

@Composable
fun PlayerScreen(
    onMinimizeClick: () -> Unit,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val song = Song(
        title = "Tacones Rojos",
        preview = "https://cdns-preview-b.dzcdn.net/stream/c-b4c5609f35dd02d6f1761a9d4f65351c-4.mp3",
        duration = 189,
        artist = Artist(
            id = 1522667852,
            name = "Sebastián Yatra"
        ),
        image = "https://e-cdns-images.dzcdn.net/images/cover/00c297f61a9a7af15833daf8ec87cc8c/500x500-000000-80-0-0.jpg"
    )

    val dimens = LocalDimensions.current
    val context = LocalContext.current

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
            PlayerController(isPlaying = false,
                onPrevious = {
                    viewModel.onCommand(context, ACTION_PREVIOUS)
                },
                onPlayPause = {
                    viewModel.onCommand(context, ACTION_PLAY_OR_PAUSE)
                },
                onNext = {
                    viewModel.onCommand(context, ACTION_NEXT)
                })
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewPlayerScreen() {
    PlayerScreen(onMinimizeClick = {})
}