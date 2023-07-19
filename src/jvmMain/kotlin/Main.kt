import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.*
import de.jxdev.espdmx.server.ui.App
import de.jxdev.espdmx.server.server.ServerThread

fun main() = application {

    // Launch Server Thread \\
    LaunchedEffect(true) {
        val serverThread = Thread(ServerThread())
        serverThread.start()
    }

    // Launch UI Stuff \\
    val isOpen = remember { mutableStateOf(true) }
    val windowState = rememberWindowState(placement = WindowPlacement.Maximized)

    Tray(
        icon = TrayIcon,
        onAction = { isOpen.value = true },
        tooltip = "ESPDMX",
        menu = {
            Item(
                text = "Open",
                onClick = { isOpen.value = true }
            )
            Item(
                "Exit",
                onClick = { exitApplication() }
            )
        }
    )
    if(isOpen.value){
        Window(
            onCloseRequest = { isOpen.value = false },
            title = "ESPDMX",
            state = windowState,
            icon = TrayIcon
        ) {
            App()
        }
    }
}


object TrayIcon : Painter() {
    override val intrinsicSize = Size(256f, 256f)
    override fun DrawScope.onDraw() {
        //drawOval(Color(0xFFFFA500))
        drawRoundRect(Color.DarkGray)
    }
}