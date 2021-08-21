package com.glothapp.api.domainbean

import com.glothapp.api.id.IdOfWord

data class WordToUpdate(
    val id: IdOfWord,
    val content: String
)
