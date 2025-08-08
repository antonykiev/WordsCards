package com.words.cards.android.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.MainEvent
import com.words.cards.presentation.intent.MainIntent
import com.words.cards.presentation.reducer.MainReducer
import com.words.cards.presentation.state.MainScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val reducer: MainReducer,
) : ViewModel(),
    StateViewModel<MainEvent, MainScreenContent, MainIntent>,
    EventHandle<MainEvent> {
    override val state: StateFlow<State<MainScreenContent, MainEvent>> = reducer.state

    override fun onIntent(intent: MainIntent) {
        viewModelScope.launch {
            reducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: MainEvent) {
        viewModelScope.launch {
            reducer.updateEvent(MainEvent.Empty)
        }
    }
}