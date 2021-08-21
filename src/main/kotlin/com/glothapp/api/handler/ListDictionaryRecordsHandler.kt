package com.glothapp.api.handler

import com.glothapp.api.apibean.DictionaryRecordForApi
import com.glothapp.api.apibean.WordForApi
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.service.DictionaryQueryService
import com.sudzhaev.auth.annotation.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ListDictionaryRecordsHandler(private val dictionaryQueryService: DictionaryQueryService) {

    @GetMapping("/list_dictionary_records")
    fun listDictionaryRecords(
        @User profileId: IdOfProfile,
        @RequestParam("dictionaryId") dictionaryId: IdOfDictionary
    ): List<DictionaryRecordForApi> {
        return dictionaryQueryService.listDictionaryRecords(profileId, dictionaryId)
            .map {
                DictionaryRecordForApi(
                    id = it.id,
                    words = it.words.map(WordForApi::from),
                    lastUpdate = it.lastUpdatedDate
                )
            }
    }
}
