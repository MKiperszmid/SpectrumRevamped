package com.mk.search_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.mk.core_ui.GrayWhite
import com.mk.core_ui.LocalDimensions
import com.mk.core_ui.Primary

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    performSearch: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = com.mk.core.R.string.search_hint),
    shouldShowHint: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
    val dimens = LocalDimensions.current
    Box(modifier = modifier) {
        BasicTextField(
            value = value, onValueChange = onValueChange,
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .padding(2.dp)
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp))
                .background(Primary)
                .fillMaxWidth()
                .padding(dimens.medium)
                .onFocusChanged {
                    onFocusChange(it)
                },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    performSearch()
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            singleLine = true,
            textStyle = MaterialTheme.typography.body1.copy(
                color = GrayWhite
            )
        )
        if (shouldShowHint) {
            Text(
                text = hint,
                color = GrayWhite,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(dimens.medium)
            )
        }

        IconButton(onClick = performSearch, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = com.mk.core.R.string.search_hint)
            )
        }
    }
}