package com.mk.home_presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mk.home_domain.model.Song

@Composable
fun HomeScreen(
    onClick: (Song) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Text(
                text = stringResource(id = com.mk.core.R.string.top_songs),
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }

        items(state.topSongs) { song ->
            Text(text = song.title)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen({

    })
}