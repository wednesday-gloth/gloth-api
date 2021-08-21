package com.glothapp.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.*

/**
 * Tech Debt:
 * 1. Exception Handling
 * 2. Common MyBatis TypeHandlers for ids
 * 3. Common jackson ids handling
 * 4. IdSetTypeHandler is weird. This about handling collections in MyBatis
 */
@SpringBootApplication
class GlothApiApplication

fun main(args: Array<String>) {
    runApplication<GlothApiApplication>(*args)
}

private fun foo() {
    val now = LocalDateTime.now()
    ZoneId.getAvailableZoneIds()
        .map { ZoneId.of(it) }
        .map { zone ->
            TextStyle.values().map {
                "($it: " + zone.getDisplayName(
                    it,
                    Locale.getDefault()
                ) + " " + zone.rules.getOffset(now) + ")"
            }
                .reduce { acc, s -> "$acc $s" } + "\n"
        }
        .forEach { println(it) }

    ZoneId.getAvailableZoneIds()
        .map { ZoneId.of(it) }
        .sortedBy { it.rules.getOffset(now) }
        .map { "${it.getDisplayName(TextStyle.NARROW, Locale.US)} (${it.rules.getOffset(now)})" }
        .forEach { println(it) }

    Locale.US.toLanguageTag()
}
