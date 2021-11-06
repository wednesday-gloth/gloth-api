package com.glothapp.api.handler

import com.glothapp.api.apibean.LanguageForApi
import com.glothapp.api.service.LanguageQueryService
import com.sudzhaev.auth.annotation.Unauthorized
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListLanguagesHandler(private val languageQueryService: LanguageQueryService) {

    @Unauthorized
    @GetMapping("/list_languages")
    // TODO: can we pass device locale as a parameter?
    fun listLanguages(): Response {
        return Response(languageQueryService.listAvailableLanguages().map(LanguageForApi::from))
    }

    data class Response(val languages: List<LanguageForApi>)
}
