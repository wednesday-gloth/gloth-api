package com.glothapp.api.handler

import com.glothapp.api.apibean.DictionaryForApi
import com.glothapp.api.domainbean.DictionaryToCreate
import com.glothapp.api.domainbean.LanguageCode
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.service.DictionaryEditService
import com.sudzhaev.auth.annotation.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
class CreateDictionaryHandler(private val dictionaryEditService: DictionaryEditService) {

    @PostMapping("/create_dictionary")
    fun createDictionary(
        @User profileId: IdOfProfile,
        @RequestBody @Valid request: CreateDictionaryRequest
    ): DictionaryForApi {
        val dictionaryToCreate = DictionaryToCreate(
            sourceLanguage = LanguageCode.from(request.sourceLang),
            targetLanguage = LanguageCode.from(request.targetLang)
        )
        val dictionary = dictionaryEditService.createDictionary(profileId, dictionaryToCreate)
        return DictionaryForApi.from(dictionary)
    }

    data class CreateDictionaryRequest(
        @field:NotBlank val sourceLang: String,
        @field:NotBlank val targetLang: String
    )
}
