package org.example.notionnotification.sse

import org.slf4j.LoggerFactory
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import reactor.core.publisher.FluxSink


data class SseConnection(val username: String, val noteId: Long, val sseEmitter:
FluxSink<ServerSentEvent<AbstractMessage>?>
) {
    private val reconnectTime: Long = 400

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun sendMessage(payload: AbstractMessage) {
        logger.info("Send message {} to connection {}", payload, this)
        val event: ServerSentEvent<AbstractMessage> = ServerSentEvent.builder<AbstractMessage>(payload)
            .build()
        sseEmitter.next(event);
    }
}