package com.words.cards.android.wordlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.WordListEvent
import com.words.cards.presentation.intent.WordListIntent
import com.words.cards.presentation.reducer.WordListReducer
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.WordListScreenContent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WordListViewModel(
    private val reducer: WordListReducer,
) : ViewModel(),
    StateViewModel<WordListEvent, WordListScreenContent, WordListIntent>,
    EventHandle<WordListEvent> {

    override val state: StateFlow<State<WordListScreenContent, WordListEvent>> = reducer.state

    override fun onIntent(intent: WordListIntent) {
        viewModelScope.launch {
            reducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: WordListEvent) {
        viewModelScope.launch {
            reducer.updateEvent(WordListEvent.Empty)
        }
    }
}