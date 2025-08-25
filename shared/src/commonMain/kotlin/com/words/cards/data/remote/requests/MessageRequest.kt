package com.words.cards.data.remote.requests

import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(
    val role: String,
    val content: String
)