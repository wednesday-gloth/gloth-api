package com.glothapp.api.dbbean

import com.glothapp.api.id.AbstractId

data class IdSet<ID : AbstractId>(val ids: Collection<ID>) {

    val rawIds = ids.map { it.id }.toTypedArray()
}
