package com.glothapp.api.config

import com.glothapp.api.util.logger
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.*

@Component
class TrackingIdServiceImpl : TrackingIdService {

    private val log = logger<TrackingIdServiceImpl>()
    private val trackingIdKey = "trackingId";

    override fun initTrackingId(): String {
        val trackingId = UUID.randomUUID().toString()
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
}
