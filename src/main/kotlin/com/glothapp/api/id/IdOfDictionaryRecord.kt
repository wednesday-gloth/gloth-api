package com.glothapp.api.id

import com.fasterxml.jackson.annotation.JsonCreator

class IdOfDictionaryRecord @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(id: Int): AbstractId(id)
