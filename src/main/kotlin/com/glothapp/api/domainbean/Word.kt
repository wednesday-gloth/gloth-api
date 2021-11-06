package com.glothapp.api.domainbean

import com.glothapp.api.id.IdOfDictionaryRecord
import com.glothapp.api.id.IdOfWord
import java.time.OffsetDateTime

data class Word(
    val id: IdOfWord,
    val recordId: IdOfDictionaryRecord,
    val content: String,
    val languageDirection: LanguageDirection,
    val startpoint: OffsetDateTime
)
