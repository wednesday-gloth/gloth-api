package com.glothapp.api.id

import com.fasterxml.jackson.annotation.JsonCreator

class IdOfWord @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(id: Int): AbstractId(id)