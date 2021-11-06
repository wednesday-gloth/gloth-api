package com.glothapp.api.service

import com.glothapp.api.domainbean.Language
import com.glothapp.api.domainbean.LanguageCode
import org.springframework.stereotype.Component
import java.util.*

@Component
class LanguageQueryServiceImpl : LanguageQueryService {

    override fun listAvailableLanguages(): Set<Language> {
        val bundle = getBundle()
        return LanguageCode.values().map { toLanguage(it, bundle) }.toSet()
    }

    override fun listLanguages(languageCodes: Collection<LanguageCode>): Set<Language> {
        val bundle = getBundle()
        return languageCodes.map { toLanguage(it, bundle) }.toSet()
    }

    private fun getBundle(): ResourceBundle {
        return ResourceBundle.getBundle("bundle/language/language")
    }

    private fun toLanguage(code: LanguageCode, i18nBundle: ResourceBundle): Language {
        return Language(code, i18nBundle.getString(code.i18nKey), code.emoji)
    }
}
