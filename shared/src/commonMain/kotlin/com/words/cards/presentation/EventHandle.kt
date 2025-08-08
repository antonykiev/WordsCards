package com.words.cards.presentation

interface EventHandle<E> {
    fun onEventHandled(event: E)
}