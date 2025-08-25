package com.words.cards.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ChatResponse(
    val choices: List<ChoiceResponse>
)