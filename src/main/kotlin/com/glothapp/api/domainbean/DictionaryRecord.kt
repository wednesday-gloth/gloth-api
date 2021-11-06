package com.glothapp.api.domainbean

import com.glothapp.api.id.IdOfDictionaryRecord
import java.time.OffsetDateTime

data class DictionaryRecord(
    val id: IdOfDictionaryRecord,
    val lastUpdatedDate: OffsetDateTime
)
