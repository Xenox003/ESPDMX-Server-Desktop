package de.jxdev.espdmx.server.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import de.jxdev.espdmx.server.ui.layout.Topbar
import de.jxdev.espdmx.server.ui.theme.CustomTheme

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    CustomTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomTheme.colors.background)
        ) {
            Topbar()
        }
    }
}