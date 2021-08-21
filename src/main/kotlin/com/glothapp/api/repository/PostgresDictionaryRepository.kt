package com.glothapp.api.repository

import com.glothapp.api.dbbean.IdSet
import com.glothapp.api.domainbean.*
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfDictionaryRecord
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.id.IdOfWord
import com.glothapp.api.mapper.DictionaryMapper
import org.springframework.stereotype.Component

@Component
class PostgresDictionaryRepository(private val dictionaryMapper: DictionaryMapper) : DictionaryRepository {

    override fun createDictionary(profileId: IdOfProfile, dictionaryToCreate: DictionaryToCreate): Dictionary {
        return dictionaryMapper.createDictionary(profileId, dictionaryToCreate)
    }

    override fun updateDictionary(profileId: IdOfProfile, dictionaryToUpdate: DictionaryToUpdate): Dictionary? {
        return dictionaryMapper.updateDictionary(profileId, dictionaryToUpdate)
    }

    override fun createDictionaryRecord(profileId: IdOfProfile, dictionaryId: IdOfDictionary): DictionaryRecord? {
        return dictionaryMapper.createDictionaryRecord(profileId, dictionaryId)
    }

    override fun deleteDictionaryRecord(profileId: IdOfProfile, recordId: IdOfDictionaryRecord): Boolean {
        return dictionaryMapper.deleteDictionaryRecord(profileId, recordId) == 1
    }

    override fun createWord(profileId: IdOfProfile, wordToCreate: WordToCreate): Word? {
        return dictionaryMapper.createWord(profileId, wordToCreate)
    }

    override fun updateWord(profileId: IdOfProfile, wordToUpdate: WordToUpdate): Word? {
        return dictionaryMapper.updateWord(profileId, wordToUpdate)
    }

    override fun deleteWord(profileId: IdOfProfile, wordId: IdOfWord): Boolean {
        return dictionaryMapper.deleteWord(profileId, wordId) == 1
    }

    override fun deleteWordsByRecordId(profileId: IdOfProfile, recordId: IdOfDictionaryRecord) {
        dictionaryMapper.deleteWordsByRecordId(profileId, recordId)
    }

    override fun findDictionaryById(profileId: IdOfProfile, dictionaryId: IdOfDictionary): Dictionary? {
        return dictionaryMapper.findDictionaryById(profileId, dictionaryId)
    }

    override fun listDictionaries(profileId: IdOfProfile): List<Dictionary> {
        return dictionaryMapper.listDictionaries(profileId)
    }

    override fun listDictionaryRecords(profileId: IdOfProfile, dictionaryId: IdOfDictionary): List<DictionaryRecord> {
        return dictionaryMapper.listDictionaryRecords(profileId, dictionaryId)
    }

    override fun listWordsByRecordIds(recordIds: Set<IdOfDictionaryRecord>): Map<IdOfDictionaryRecord, List<Word>> {
        if (recordIds.isEmpty()) {
            return mapOf()
        }
        return dictionaryMapper.listWordsByRecordIds(IdSet(recordIds)).groupBy { it.recordId }
    }
}
