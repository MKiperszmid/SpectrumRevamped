package com.mk.player_presentation.components

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Slider
import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.mk.core_ui.Accent
import com.mk.core_ui.GraySemiWhite
import com.mk.core_ui.White

@Composable
fun PlayerSlider(
    duration: Int,
    currentValue: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = currentValue,
            onValueChange = {
                onValueChange(it)
            },
            valueRange = 0f..duration.toFloat(),
            colors = SliderDefaults.colors(
                thumbColor = Accent,
                activeTrackColor = Accent,
                inactiveTrackColor = White.copy(alpha = 0.2f)
            )
        )
        Text(
            text = "${DateUtils.formatElapsedTime(currentValue.toLong())}/${DateUtils.formatElapsedTime(duration.toLong())}",
            color = GraySemiWhite,
            fontSize = 12.sp
        )
    }
}