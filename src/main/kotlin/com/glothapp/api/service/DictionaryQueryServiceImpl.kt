package com.glothapp.api.service

import com.glothapp.api.domainbean.Dictionary
import com.glothapp.api.domainbean.DictionaryRecordWithWords
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.repository.DictionaryRepository
import org.springframework.stereotype.Component

@Component
class DictionaryQueryServiceImpl(
    private val dictionaryRepository: DictionaryRepository
) : DictionaryQueryService {

    // unused. Should be deleted
    override fun findDictionaryById(profileId: IdOfProfile, dictionaryId: IdOfDictionary): Dictionary {
        return dictionaryRepository.findDictionaryById(profileId, dictionaryId)
            ?: throw IllegalArgumentException("Dictionary not found")
    }

    override fun listDictionaries(profileId: IdOfProfile): List<Dictionary> {
        return dictionaryRepository.listDictionaries(profileId)
    }

    override fun listDictionaryRecords(
        profileId: IdOfProfile,
        dictionaryId: IdOfDictionary
    ): List<DictionaryRecordWithWords> {
        val records = dictionaryRepository.listDictionaryRecords(profileId, dictionaryId)
        val recordToWordsMap = dictionaryRepository.listWordsByRecordIds(records.map { it.id }.toSet())
        return records
            .map { DictionaryRecordWithWords(it.id, it.lastUpdatedDate, recordToWordsMap[it.id] ?: listOf()) }
            .filter { it.words.isNotEmpty() }
    }
}
