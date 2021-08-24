package com.glothapp.api.handler

import com.glothapp.api.apibean.DictionaryForApi
import com.glothapp.api.apibean.LanguageForApi
import com.glothapp.api.id.IdOfProfile
import com.glothapp.api.service.DictionaryQueryService
import com.glothapp.api.service.LanguageQueryService
import com.sudzhaev.auth.annotation.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListDictionariesHandler(
    private val dictionaryQueryService: DictionaryQueryService,
    private val languageQueryService: LanguageQueryService
) {

    @GetMapping("/list_dictionaries")
    // TODO: can we pass device locale as a parameter?
    fun listDictionaries(@User profileId: IdOfProfile): Response {
        val dictionaries = dictionaryQueryService.listDictionaries(profileId)
        val languageCodes = dictionaries.flatMap { setOf(it.sourceLanguage, it.targetLanguage) }.toSet()
        val languages = languageQueryService.listLanguages(languageCodes)
        return Response(
            dictionaries.map(DictionaryForApi::from),
            languages.map(LanguageForApi::from)
        )
    }

    data class Response(
        val dictionaries: List<DictionaryForApi>,
        val languages: List<LanguageForApi>
    )
}
