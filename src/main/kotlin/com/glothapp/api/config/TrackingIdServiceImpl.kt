package com.glothapp.api.config

import com.glothapp.api.util.logger
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.*
import kotlin.random.Random

@Component
class TrackingIdServiceImpl : TrackingIdService {

    private val log = logger<TrackingIdServiceImpl>()
    private val trackingIdKey = "trackingId";

    override fun initTrackingId(): String {
        val trackingId = generateRandomString()
        MDC.put(trackingIdKey, trackingId)
        return trackingId
    }

    override fun getTrackingId(): String {
        val trackingId = MDC.get(trackingIdKey)
        if (trackingId != null) {
            return trackingId
        }
        val substituteId = "NULL_TRACKING_ID_" + UUID.randomUUID().toString()
        log.error("tracking id was not initialized property. Returning substitute id = {}", substituteId)
        return substituteId
    }

    override fun clearTrackingId() {
        MDC.remove(trackingIdKey)
    }

    private fun generateRandomString(): String {
        return generateSequence { Random.nextInt('0'.code, 'z'.code) }
            .filter { i -> (i <= '9'.code || i >= 'A'.code) && (i <= 'Z'.code || i >= 'a'.code) }
            .take(12)
            .map { it.toChar() }
            .joinToString(separator = "")
    }
}
