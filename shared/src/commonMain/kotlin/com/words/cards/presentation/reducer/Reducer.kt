package com.words.cards.presentation.reducer

import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

interface Reducer<E, C, I> {
    val mutableState: MutableStateFlow<State<C, E>>
    val state: StateFlow<State<C, E>> get() = mutableState

    suspend fun processIntent(intent: I)

    fun updateEvent(event: E) {
        mutableState.update {
            it.copy(event = event)
        }
    }

    suspend fun updateContent(content: (C) -> C) {
        mutableState.update {
            it.copy(content = content(it.content))
        }
    }
}