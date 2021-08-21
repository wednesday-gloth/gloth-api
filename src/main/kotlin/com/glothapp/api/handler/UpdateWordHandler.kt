package com.glothapp.api.handler

import com.glothapp.api.apibean.WordForApi
import com.glothapp.api.domainbean.WordToUpdate
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.id.IdOfWord
import com.glothapp.api.service.DictionaryEditService
import com.sudzhaev.auth.annotation.User
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
class UpdateWordHandler(private val dictionaryEditService: DictionaryEditService) {

    @PutMapping
    fun updateWord(
        @User profileId: IdOfProfile,
        @RequestBody @Valid request: UpdateWordRequest
    ): WordForApi {
        val updatedWord = dictionaryEditService.updateWord(
            profileId,
            WordToUpdate(request.id, request.content.trim())
        )
        return WordForApi.from(updatedWord)
    }

    data class UpdateWordRequest(
        val id: IdOfWord,
        @field:NotBlank val content: String
    )
}
