package de.jxdev.espdmx.server.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.jxdev.espdmx.server.ui.theme.CustomTheme

@Composable
fun Topbar() {
    Box (
        modifier = Modifier
            .background(CustomTheme.colors.primary)
            .fillMaxWidth()
            .fillMaxHeight(0.05f)
    ) {
        Button (
            onClick = {}
        ) {
            Text(text = "test")
        }
    }
}