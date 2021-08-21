package com.glothapp.api.config.filter

import com.glothapp.api.config.TrackingIdService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TrackingIdFilter(private val trackingIdService: TrackingIdService) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            trackingIdService.initTrackingId()
            filterChain.doFilter(request, response)
        } finally {
            trackingIdService.clearTrackingId()
        }
    }
}
