package com.mk.player_presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mk.core_ui.Accent
import com.mk.core_ui.GrayWhite
import com.mk.core_ui.Primary

@Composable
fun PlayerController(
    isPlaying: Boolean,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "favorite", tint = GrayWhite)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.SkipPrevious, contentDescription = "previous", tint = GrayWhite)
        }
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(50.dp)) {
            Box {
                Surface(shape = CircleShape, color = Primary) {
                    if (isPlaying) {
                        Icon(
                            imageVector = Icons.Default.Pause,
                            contentDescription = "pause",
                            tint = Accent,
                            modifier = Modifier.size(50.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "play",
                            tint = Accent,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.SkipNext, contentDescription = "next", tint = GrayWhite)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "options", tint = GrayWhite)
        }
    }
}

@Preview
@Composable
fun PreviewPlayerController() {
    PlayerController(false)
}