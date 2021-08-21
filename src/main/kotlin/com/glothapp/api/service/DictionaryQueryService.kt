package com.glothapp.api.service

import com.glothapp.api.domainbean.Dictionary
import com.glothapp.api.domainbean.DictionaryRecordWithWords
import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfProfile

interface DictionaryQueryService {

    fun findDictionaryById(profileId: IdOfProfile, dictionaryId: IdOfDictionary): Dictionary

    fun listDictionaries(profileId: IdOfProfile): List<Dictionary>

    fun listDictionaryRecords(profileId: IdOfProfile, dictionaryId: IdOfDictionary): List<DictionaryRecordWithWords>
}
