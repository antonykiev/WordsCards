package com.words.cards.presentation

import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.StateFlow

interface StateViewModel<E, C, I> {

    val state: StateFlow<State<C, E>>

    fun onIntent(intent: I)
}