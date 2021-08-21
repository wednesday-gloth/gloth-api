package com.glothapp.api.domainbean

import com.glothapp.api.id.IdOfDictionaryRecord

data class WordToCreate(
    val recordId: IdOfDictionaryRecord,
    val content: String,
    val languageDirection: LanguageDirection
)
