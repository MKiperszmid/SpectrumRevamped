package com.mk.search_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mk.core_ui.Gray
import com.mk.core_ui.LocalDimensions
import com.mk.core_ui.Primary
import com.mk.core_ui.White
import com.mk.seach_domain.model.Song

@Composable
fun SearchResultItem(
    song: Song,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimens = LocalDimensions.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            ), elevation = 5.dp
    ) {
        Row(
            modifier = Modifier.background(Primary)
        ) {
            AsyncImage(
                model = song.image,
                contentDescription = song.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(75.dp)
            )
            Spacer(modifier = Modifier.height(dimens.extraSmall))
            Column(modifier = Modifier.padding(dimens.extraSmall)) {
                Text(
                    text = song.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(dimens.extraSmall))
                Text(
                    text = song.artistName,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearchResultItem() {
    SearchResultItem(
        song = Song(
            1, "This is a very long long long long song name", "", 1, "This is a very long long long long long long long artist name",
            ""
        ), onClick = { }
    )
}