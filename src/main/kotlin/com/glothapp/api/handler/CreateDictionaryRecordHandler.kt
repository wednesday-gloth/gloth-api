package com.glothapp.api.handler

import com.glothapp.api.apibean.DictionaryRecordForApi
import com.glothapp.api.apibean.LanguageDirectionForApi
import com.glothapp.api.apibean.WordForApi
import com.glothapp.api.domainbean.WordToCreate
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.service.DictionaryEditService
import com.sudzhaev.auth.annotation.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
class CreateDictionaryRecordHandler(private val dictionaryEditService: DictionaryEditService) {

    @PostMapping("/create_dictionary_record")
    fun createDictionaryRecord(
        @User profileId: IdOfProfile,
        @RequestBody @Valid request: CreateDictionaryRecordRequest
    ): DictionaryRecordForApi {
        val record = dictionaryEditService.createDictionaryRecord(profileId, request.dictionaryId)
        val word = dictionaryEditService.createWord(
            profileId,
            WordToCreate(record.id, request.word.content, request.word.lang.domainValue)
        )
        return DictionaryRecordForApi(
            id = record.id,
            words = listOf(WordForApi.from(word)),
            lastUpdate = record.lastUpdatedDate
        )
    }

    data class CreateDictionaryRecordRequest(
        val dictionaryId: IdOfDictionary,
        val word: Word
    ) {

        data class Word(
            @field:NotBlank val content: String,
            val lang: LanguageDirectionForApi
        )
    }

}
