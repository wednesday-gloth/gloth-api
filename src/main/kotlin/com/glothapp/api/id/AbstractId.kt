package com.glothapp.api.id

import com.fasterxml.jackson.annotation.JsonValue

abstract class AbstractId(@JsonValue val id: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }
        return this.id == (other as? AbstractId)?.id
    }

    override fun hashCode(): Int {
        return this.id
    }

    override fun toString(): String {
        return "${javaClass.simpleName}($id)"
    }
}
