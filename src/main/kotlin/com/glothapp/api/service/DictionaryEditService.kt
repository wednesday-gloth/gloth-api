package com.glothapp.api.service

import com.glothapp.api.domainbean.*
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfDictionaryRecord
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.id.IdOfWord

interface DictionaryEditService {

    fun createDictionary(profileId: IdOfProfile, dictionaryToCreate: DictionaryToCreate): Dictionary

    fun updateDictionary(profileId: IdOfProfile, dictionaryToUpdate: DictionaryToUpdate): Dictionary

    fun createDictionaryRecord(profileId: IdOfProfile, dictionaryId: IdOfDictionary): DictionaryRecord

    fun deleteDictionaryRecord(profileId: IdOfProfile, recordId: IdOfDictionaryRecord)

    fun createWord(profileId: IdOfProfile, wordToCreate: WordToCreate): Word

    fun updateWord(profileId: IdOfProfile, wordToUpdate: WordToUpdate): Word

    fun deleteWord(profileId: IdOfProfile, wordId: IdOfWord)
}
