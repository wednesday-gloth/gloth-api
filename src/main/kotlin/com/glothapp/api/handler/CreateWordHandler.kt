package com.glothapp.api.handler

import com.glothapp.api.apibean.LanguageDirectionForApi
import com.glothapp.api.apibean.WordForApi
import com.glothapp.api.domainbean.WordToCreate
import com.glothapp.api.id.IdOfDictionaryRecord
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.service.DictionaryEditService
import com.sudzhaev.auth.annotation.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
class CreateWordHandler(private val dictionaryEditService: DictionaryEditService) {

    @PostMapping("/create_word")
    fun createWord(
        @User profileId: IdOfProfile,
        @RequestBody @Valid request: Request
    ): Response {
        val word = dictionaryEditService.createWord(
            profileId,
            WordToCreate(
                request.recordId,
                request.content.trim(),
                request.lang.domainValue
            )
        )
        return Response(WordForApi.from(word))
    }

    data class Request(
        val recordId: IdOfDictionaryRecord,
        @field:NotBlank val content: String,
        val lang: LanguageDirectionForApi
    )

    data class Response(val word: WordForApi)
}
