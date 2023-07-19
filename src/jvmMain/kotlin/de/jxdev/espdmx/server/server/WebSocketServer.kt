package de.jxdev.espdmx.server.server

import com.google.gson.GsonBuilder
import de.jxdev.espdmx.lib.serialization.CommandSerializer
import de.jxdev.espdmx.lib.websocket.Command
import de.jxdev.espdmx.lib.websocket.server.StatusDmxCommandArgs
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.LinkedHashSet
import kotlin.concurrent.fixedRateTimer

val sessions: MutableSet<WebSocketSession> = Collections.synchronizedSet(LinkedHashSet<WebSocketSession>())
fun Application.main() {
    install(plugin = WebSockets)

    routing {
        webSocket("/ws") {
            println("New client connected $this")
            sessions += this

            try {
                while (true) {
                    val frame = incoming.receive()
                    if (frame is Frame.Text) {
                        val receivedText = frame.readText()
                        println("new Message: $receivedText")

                        outgoing.send(Frame.Text("{}"))
                    }
                }
            } catch (e: Exception) {
                println("Socket Error: ${e.localizedMessage}")
            } finally {
                println("Client disconnected $this")
                sessions -= this
            }
        }
    }

    fixedRateTimer("timer", false, 0L, 100) {
        val gson = GsonBuilder().registerTypeAdapter(Command::class.java, CommandSerializer()).create()

        broadcast(gson.toJson(Command(
            baseCommand = "STATUS",
            subCommand = "DMX",
            args = StatusDmxCommandArgs(
                list = arrayListOf(1,0,5,100,200,500,12304,234,1203,4,0,0,50,50,0,213,21,4021340,500)
            )
        )))
    }
}

fun broadcast(message: String) {
    sessions.forEach {session ->
        GlobalScope.launch {
            session.send(message)
        }
    }
}