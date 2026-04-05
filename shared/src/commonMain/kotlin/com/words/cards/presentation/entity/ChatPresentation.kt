package com.words.cards.presentation.entity

import kotlinx.serialization.Serializable

@Serializable
data class ChatPresentation(
    val translation : List<String>,
    val description : String,
    val sentences : List<String>,
)
