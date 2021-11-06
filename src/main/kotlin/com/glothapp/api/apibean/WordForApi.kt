package com.glothapp.api.apibean

import com.glothapp.api.domainbean.Word
import com.glothapp.api.id.IdOfWord
import java.time.OffsetDateTime

data class WordForApi(
    val id: IdOfWord,
    val content: String,
    val lang: LanguageDirectionForApi,
    val startpoint: OffsetDateTime
) {

    companion object {

        fun from(word: Word): WordForApi {
            return WordForApi(
                id = word.id,
                content = word.content,
                lang = LanguageDirectionForApi.from(word.languageDirection),
                startpoint = word.startpoint
            )
        }
    }
}
