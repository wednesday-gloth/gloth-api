package com.glothapp.api.apibean

import com.glothapp.api.domainbean.Language

data class LanguageForApi(
    val code: String,
    val emoji: String,
    val name: String
) {

    companion object {

        fun from(language: Language): LanguageForApi {
            return LanguageForApi(
                code = language.code.code,
                emoji = language.emoji,
                name = language.name
            )
        }
    }
}
