package com.words.cards.android.word

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.WordEvent
import com.words.cards.presentation.event.WordListEvent
import com.words.cards.presentation.intent.WordIntent
import com.words.cards.presentation.reducer.NewWordReducer
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.WordScreenContent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewWordViewModel(
    private val reducer: NewWordReducer,
) : ViewModel(),
    StateViewModel<WordEvent, WordScreenContent, WordIntent>,
    EventHandle<WordListEvent> {

    override val state: StateFlow<State<WordScreenContent, WordEvent>> = reducer.state

    override fun onIntent(intent: WordIntent) {
        viewModelScope.launch {
            reducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: WordListEvent) {
        viewModelScope.launch {
            reducer.updateEvent(WordEvent.Empty)
        }
    }
}