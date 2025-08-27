package com.words.cards.android.word_new

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.WordNewEvent
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
    StateViewModel<WordNewEvent, WordScreenContent, WordIntent>,
    EventHandle<WordNewEvent> {

    override val state: StateFlow<State<WordScreenContent, WordNewEvent>> = reducer.state

    override fun onIntent(intent: WordIntent) {
        viewModelScope.launch {
            reducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: WordNewEvent) {
        viewModelScope.launch {
            reducer.updateEvent(WordNewEvent.Empty)
        }
    }
}