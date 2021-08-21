package com.glothapp.api.config

import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.*

@Component
class TrackingIdServiceImpl : TrackingIdService {

    private val trackingIdKey = "trackingId";

    override fun initTrackingId(): String {
        val trackingId = UUID.randomUUID().toString()
        MDC.put(trackingIdKey, trackingId)
        return trackingId
    }

    override fun getTrackingId(): String {
        return MDC.get(trackingIdKey) ?: ("TRACKING_ID_NOT_FOUND_" + UUID.randomUUID().toString())
    }

    override fun clearTrackingId() {
        MDC.remove(trackingIdKey)
    }
}
