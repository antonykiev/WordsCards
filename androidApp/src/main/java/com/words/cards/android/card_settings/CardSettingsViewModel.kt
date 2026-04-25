package com.words.cards.android.card_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.CardSettingsEvent
import com.words.cards.presentation.intent.CardSettingsIntent
import com.words.cards.presentation.reducer.CardSettingsReducer
import com.words.cards.presentation.state.CardSettingsScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CardSettingsViewModel(
    private val reducer: CardSettingsReducer,
) : ViewModel(),
    StateViewModel<CardSettingsEvent, CardSettingsScreenContent, CardSettingsIntent>,
    EventHandle<CardSettingsEvent> {

    override val state: StateFlow<State<CardSettingsScreenContent, CardSettingsEvent>> = reducer.state

    override fun onIntent(intent: CardSettingsIntent) {
        viewModelScope.launch {
            reducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: CardSettingsEvent) {
        viewModelScope.launch {
            reducer.updateEvent(CardSettingsEvent.Empty)
        }
    }
}
