package com.glothapp.api.id

import com.fasterxml.jackson.annotation.JsonCreator

// TODO: can we write common jackson implementation for all ids instead of annotating every constructor?
class IdOfDictionary @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(id: Int) : AbstractId(id)
