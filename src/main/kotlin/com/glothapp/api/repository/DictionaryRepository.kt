package com.glothapp.api.repository

import com.glothapp.api.domainbean.*
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfDictionaryRecord
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.id.IdOfWord

interface DictionaryRepository {

    fun createDictionary(profileId: IdOfProfile, dictionaryToCreate: DictionaryToCreate): Dictionary

    fun updateDictionary(profileId: IdOfProfile, dictionaryToUpdate: DictionaryToUpdate): Dictionary?

    fun createDictionaryRecord(profileId: IdOfProfile, dictionaryId: IdOfDictionary): DictionaryRecord?

    fun deleteDictionaryRecord(profileId: IdOfProfile, recordId: IdOfDictionaryRecord): Boolean

    fun createWord(profileId: IdOfProfile, wordToCreate: WordToCreate): Word?

    fun updateWord(profileId: IdOfProfile, wordToUpdate: WordToUpdate): Word?

    fun deleteWord(profileId: IdOfProfile, wordId: IdOfWord): Boolean

    fun deleteWordsByRecordId(profileId: IdOfProfile, recordId: IdOfDictionaryRecord)

    fun findDictionaryById(profileId: IdOfProfile, dictionaryId: IdOfDictionary): Dictionary?

    fun listDictionaries(profileId: IdOfProfile): List<Dictionary>

    fun listDictionaryRecords(profileId: IdOfProfile, dictionaryId: IdOfDictionary): List<DictionaryRecord>

    fun listWordsByRecordIds(recordIds: Set<IdOfDictionaryRecord>): Map<IdOfDictionaryRecord, List<Word>>
}
