package com.glothapp.api.config

interface TrackingIdService {

    fun initTrackingId(): String

    fun getTrackingId(): String

    fun clearTrackingId()
}
