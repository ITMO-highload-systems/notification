package org.example.notionnotification.sse

data class Message(
    override val type: Type,
    val message: String,
    val noteId: Long
) : AbstractMessage(type)