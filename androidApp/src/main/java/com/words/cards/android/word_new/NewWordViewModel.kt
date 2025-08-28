package com.words.cards.android.word_new

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.NewWordEvent
import com.words.cards.presentation.intent.NewWordIntent
import com.words.cards.presentation.reducer.NewWordReducer
import com.words.cards.presentation.state.State
import com.words.cards.presentation.state.NewWordScreenContent
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewWordViewModel(
    private val reducer: NewWordReducer,
) : ViewModel(),
    StateViewModel<NewWordEvent, NewWordScreenContent, NewWordIntent>,
    EventHandle<NewWordEvent> {

    override val state: StateFlow<State<NewWordScreenContent, NewWordEvent>> = reducer.state

    override fun onIntent(intent: NewWordIntent) {
        viewModelScope.launch {
            reducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: NewWordEvent) {
        viewModelScope.launch {
            reducer.updateEvent(NewWordEvent.Empty)
        }
    }
}