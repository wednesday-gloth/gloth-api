package com.glothapp.api.domainbean

/**
 * codes taken from https://yandex.ru/dev/translate/doc/dg/concepts/api-overview.html#api-overview__languages
 */
enum class LanguageCode(val code: String, val emoji: String, val i18nKey: String = code) {
    RUSSIAN("ru", "🇷🇺"),
    ENGLISH("en", "🇬🇧"),
    GERMAN("de", "🇩🇪"),
    SPANISH("es", "🇪🇸"),
    CHINESE("zh", "🇨🇳");

    companion object {
        private val codeToLanguageMap = values().associateBy { it.code }

        fun from(code: String): LanguageCode {
            return codeToLanguageMap[code] ?: throw IllegalArgumentException("Language not resolved: $code")
        }
    }
}
