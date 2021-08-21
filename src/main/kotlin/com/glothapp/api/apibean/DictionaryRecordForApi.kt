package com.glothapp.api.apibean

import com.glothapp.api.id.IdOfDictionaryRecord
import java.time.OffsetDateTime

data class DictionaryRecordForApi(
    val id: IdOfDictionaryRecord,
    val words: List<WordForApi>,
    val lastUpdate: OffsetDateTime
)
