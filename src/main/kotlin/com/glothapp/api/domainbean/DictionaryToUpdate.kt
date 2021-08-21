package com.glothapp.api.domainbean

import com.glothapp.api.id.IdOfDictionary

data class DictionaryToUpdate(
    val id: IdOfDictionary,
    val sourceLanguage: LanguageCode,
    val targetLanguage: LanguageCode
)
