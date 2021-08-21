package com.glothapp.api.config.auth

import com.glothapp.api.id.IdOfProfile
import com.sudzhaev.auth.SessionService
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class MockSessionService: SessionService<IdOfProfile> {

    override fun createSession(user: IdOfProfile, response: HttpServletResponse) {
        TODO("Not yet implemented")
    }

    override fun resolveUser(request: HttpServletRequest): IdOfProfile? {
        return IdOfProfile(request.getHeader("X-SESSION").toInt())
    }
}
