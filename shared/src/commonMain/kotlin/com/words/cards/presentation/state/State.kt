package com.words.cards.presentation.state

data class State<C, E>(
    val isLoading: Boolean,
    val event: E,
    val content: C,
)