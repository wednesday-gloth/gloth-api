package com.glothapp.api.domainbean

import com.glothapp.api.id.IdOfDictionaryRecord
import java.time.OffsetDateTime

data class DictionaryRecordWithWords(
    val id: IdOfDictionaryRecord,
    val lastUpdatedDate: OffsetDateTime,
    val words: List<Word>
)
