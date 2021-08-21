package com.glothapp.api.domainbean

import com.glothapp.api.id.IdOfDictionary
import com.glothapp.api.id.IdOfProfile
import java.time.OffsetDateTime

data class Dictionary(
    val id: IdOfDictionary,
    val profileId: IdOfProfile,
    val sourceLanguage: LanguageCode,
    val targetLanguage: LanguageCode,
    val startpoint: OffsetDateTime
)
