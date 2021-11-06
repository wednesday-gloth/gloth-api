package com.glothapp.api.domainbean

enum class LanguageDirection(val code: String) {
    SOURCE("source"),
    TARGET("target");

    companion object {
        private val codeToValueMap = values().associateBy { it.code }

        fun from(code: String): LanguageDirection {
            return codeToValueMap[code] ?: throw IllegalArgumentException("LanguageDirection not resolved: $code")
        }
    }
}
