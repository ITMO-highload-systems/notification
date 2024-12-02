package org.example.notionnotification.sse

data class ExecMessage(
    override val type: Type,
    val message: String,
    val noteId: Long,
    val paragraphId: Long
) : AbstractMessage(type)