package com.glothapp.api.service

import com.glothapp.api.domainbean.*
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfDictionaryRecord
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.id.IdOfWord
import com.glothapp.api.repository.DictionaryRepository
import org.springframework.stereotype.Component

@Component
class DictionaryEditServiceImpl(
    private val dictionaryRepository: DictionaryRepository
) : DictionaryEditService {

    override fun createDictionary(profileId: IdOfProfile, dictionaryToCreate: DictionaryToCreate): Dictionary {
        return dictionaryRepository.createDictionary(profileId, dictionaryToCreate)
    }

    override fun updateDictionary(profileId: IdOfProfile, dictionaryToUpdate: DictionaryToUpdate): Dictionary {
        return dictionaryRepository.updateDictionary(profileId, dictionaryToUpdate)
            ?: throw IllegalArgumentException("Dictionary not updated")
    }

    override fun createDictionaryRecord(profileId: IdOfProfile, dictionaryId: IdOfDictionary): DictionaryRecord {
        return dictionaryRepository.createDictionaryRecord(profileId, dictionaryId)
            ?: throw IllegalArgumentException("Record not created")
    }

    override fun deleteDictionaryRecord(profileId: IdOfProfile, recordId: IdOfDictionaryRecord) {
        dictionaryRepository.deleteWordsByRecordId(profileId, recordId)
        require(dictionaryRepository.deleteDictionaryRecord(profileId, recordId)) { "Record not deleted" }
    }

    override fun createWord(profileId: IdOfProfile, wordToCreate: WordToCreate): Word {
        return dictionaryRepository.createWord(profileId, wordToCreate)
            ?: throw IllegalArgumentException("Word not created")
    }

    override fun updateWord(profileId: IdOfProfile, wordToUpdate: WordToUpdate): Word {
        return dictionaryRepository.updateWord(profileId, wordToUpdate)
            ?: throw IllegalArgumentException("Word not updated")
    }

    override fun deleteWord(profileId: IdOfProfile, wordId: IdOfWord) {
        require(dictionaryRepository.deleteWord(profileId, wordId)) { "Word not deleted" }
    }
}
