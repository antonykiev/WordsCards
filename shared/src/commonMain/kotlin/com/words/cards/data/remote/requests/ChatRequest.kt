package com.words.cards.data.remote.requests

import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(
    val model: String,
    val stream: Boolean,
    val max_tokens: Int,
    val temperature: Double,
    val top_p: Double,
    val messages: List<MessageRequest>
)