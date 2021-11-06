package com.glothapp.api.handler

import com.glothapp.api.id.IdOfDictionaryRecord
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.service.DictionaryEditService
import com.sudzhaev.auth.annotation.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DeleteDictionaryRecordHandler(private val dictionaryEditService: DictionaryEditService) {

    @PostMapping("/delete_dictionary_record")
    fun deleteDictionaryRecord(
        @User profileId: IdOfProfile,
        @RequestParam("id") recordId: IdOfDictionaryRecord
    ) {
        dictionaryEditService.deleteDictionaryRecord(profileId, recordId)
    }
}
