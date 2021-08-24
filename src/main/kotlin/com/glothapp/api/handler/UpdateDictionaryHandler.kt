package com.glothapp.api.handler

import com.glothapp.api.apibean.DictionaryForApi
import com.glothapp.api.domainbean.DictionaryToUpdate
import com.glothapp.api.domainbean.LanguageCode
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.service.DictionaryEditService
import com.sudzhaev.auth.annotation.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UpdateDictionaryHandler(private val dictionaryEditService: DictionaryEditService) {

    @PostMapping("/update_dictionary")
    fun updateDictionary(
        @User profileId: IdOfProfile,
        @RequestBody @Valid request: Request
    ): Response {
        val dictionary = dictionaryEditService.updateDictionary(
            profileId, DictionaryToUpdate(
                id = request.id,
                sourceLanguage = LanguageCode.from(request.sourceLang),
                targetLanguage = LanguageCode.from(request.targetLang)
            )
        )
        return Response(DictionaryForApi.from(dictionary))
    }

    data class Request(
        val id: IdOfDictionary,
        val sourceLang: String,
        val targetLang: String
    )

    data class Response(val dictionary: DictionaryForApi)
}
