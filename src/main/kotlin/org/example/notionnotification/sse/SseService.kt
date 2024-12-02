package org.example.notionnotification.sse

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.codec.ServerSentEvent
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.util.*
import java.util.function.Consumer
import kotlin.collections.HashMap

@Service
class SseService {
    private val sseConnections: MutableMap<Long, MutableList<SseConnection>> = HashMap()
    @Value("\${sse.timeout}")
    lateinit var timeout : String

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun openConnection(username: String, noteId: Long): Flux<ServerSentEvent<AbstractMessage>> {
//        val sseConnection = SseConnection(username, noteId, SseEmitter(timeout.toLong()))
//        sseConnections.computeIfAbsent(noteId) { ArrayList() }.add(sseConnection)
//        logger.info("Connection open for username {} and note_id {}", username, noteId)
//
//        sseConnection.sseEmitter.onCompletion {
//            logger.info(
//                "Released SSE connection {}",
//                sseConnection
//            )
//        }
//        sseConnection.sseEmitter.onTimeout {
//            logger.info(
//                "SSE timeout {}",
//                sseConnection
//            )
//            sseConnections[noteId]?.remove(sseConnection)
//        }
//        sseConnection.sseEmitter.onError { error ->
//            logger.warn(
//                "SSE error {}, connection {}",
//                error.message,
//                sseConnection
//            )
//            sseConnections[noteId]?.remove(sseConnection)
//        }
//
//        return sseConnection

        return Flux.create<ServerSentEvent<AbstractMessage>?>(Consumer<FluxSink<ServerSentEvent<AbstractMessage>?>> { fluxSink: FluxSink<ServerSentEvent<AbstractMessage>?> ->  // 3

            val sseConnection = SseConnection(username, noteId, fluxSink)
            fluxSink.onCancel() // 4
            {
                sseConnections[noteId]?.remove(sseConnection)
            }

            sseConnections.computeIfAbsent(noteId) { ArrayList() }.add(sseConnection)

        })
    }

    fun sendMessage(noteId: Long, payload: AbstractMessage) {
        logger.info("send message {}", payload)
        sseConnections[noteId]?.forEach(Consumer { sseConnection: SseConnection ->
                sseConnection.sendMessage(payload)
            })
    }
}