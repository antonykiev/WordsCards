package com.words.cards.presentation.state

sealed interface LoadableContent {
    data object Loading : LoadableContent

    data class Loaded<T>(
        val data: T
    ) : LoadableContent

    data class Error(
        val error: Throwable
    ) : LoadableContent
}