package com.glothapp.api.apibean

import com.fasterxml.jackson.annotation.JsonValue
import com.glothapp.api.domainbean.LanguageDirection

enum class LanguageDirectionForApi(val domainValue: LanguageDirection, @JsonValue val jsonValue: String) {
    TARGET(LanguageDirection.TARGET, "target"),
    SOURCE(LanguageDirection.SOURCE, "source");

    companion object {
        private val domainValueToEnumMap = values().associateBy { it.domainValue }

        fun from(domainValue: LanguageDirection): LanguageDirectionForApi {
            return domainValueToEnumMap[domainValue]
                ?: throw IllegalArgumentException("unexpected domainValue: $domainValue")
        }
    }
}
