package org.example.notionnotification.sse

import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux


@RestController
@RequestMapping("/sse")
class SseController(private val sseConnectionService: SseService) {

    @GetMapping(path = ["/bind/{noteId}/{username}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun bindUser(@PathVariable noteId: Long, @PathVariable username: String):
            Flux<ServerSentEvent<AbstractMessage>> {

        return sseConnectionService.openConnection(
            username,
            noteId
        )
    }

    @PostMapping(path = ["/send/{noteId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun sendMessage(@PathVariable noteId: Long, @RequestBody message: Message) {
        sseConnectionService.sendMessage(noteId, message)
    }
}