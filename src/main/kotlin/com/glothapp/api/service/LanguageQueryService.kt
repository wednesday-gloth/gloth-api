package com.glothapp.api.service

import com.glothapp.api.domainbean.Language
import com.glothapp.api.domainbean.LanguageCode

interface LanguageQueryService {

    fun listAvailableLanguages(): Set<Language>

    fun listLanguages(languageCodes: Collection<LanguageCode>): Set<Language>
}
