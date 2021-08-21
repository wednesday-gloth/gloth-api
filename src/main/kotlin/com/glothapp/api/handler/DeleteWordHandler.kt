package com.glothapp.api.handler

import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.id.IdOfWord
import com.glothapp.api.service.DictionaryEditService
import com.sudzhaev.auth.annotation.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DeleteWordHandler(private val dictionaryEditService: DictionaryEditService) {

    @PostMapping("/delete_word")
    fun deleteWord(
        @User profileId: IdOfProfile,
        @RequestParam("id") wordId: IdOfWord
    ) {
        dictionaryEditService.deleteWord(profileId, wordId)
        // Should we delete record if there are no words anymore?
        // And maybe return flag that record was deleted as well
    }
}
