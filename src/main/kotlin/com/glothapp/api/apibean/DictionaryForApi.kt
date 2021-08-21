package com.glothapp.api.apibean

import com.glothapp.api.domainbean.Dictionary
import com.glothapp.api.id.IdOfDictionary

data class DictionaryForApi(
    val id: IdOfDictionary,
    val sourceLangCode: String,
    val targetLangCode: String
) {

    companion object {

        fun from(dictionary: Dictionary): DictionaryForApi {
            return DictionaryForApi(
                id = dictionary.id,
                sourceLangCode = dictionary.sourceLanguage.code,
                targetLangCode = dictionary.targetLanguage.code
            )
        }
    }
}
