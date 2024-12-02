package org.example.notionnotification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class NotionNotificationApplication

fun main(args: Array<String>) {
    runApplication<NotionNotificationApplication>(*args)
}
