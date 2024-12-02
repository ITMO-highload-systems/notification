package org.example.notionnotification.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.StringDeserializer
import org.example.notionnotification.sse.ExecMessage
import org.example.notionnotification.sse.Message
import org.example.notionnotification.sse.SseService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaListener(private val sseService: SseService, private val objectMapper: ObjectMapper) {

    @KafkaListener(topics = ["events"], groupId = "group1")
    fun listenerEvents(data: String) {
        val value = objectMapper.readValue<Message>(data, Message::class.java)
        sseService.sendMessage(value.noteId, value)
        println(data)
    }

    @KafkaListener(topics = ["executions"], groupId = "group1")
    fun listenerExecutions(data: String) {
        val value = objectMapper.readValue(data, ExecMessage::class.java)
        sseService.sendMessage(value.noteId, value)
        println(data)
    }
}