package com.mk.player_presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mk.player_domain.model.Artist
import com.mk.player_domain.model.Song

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
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = song.image,
            contentDescription = song.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlayerScreen() {
    PlayerScreen(onMinimizeClick = {})
}