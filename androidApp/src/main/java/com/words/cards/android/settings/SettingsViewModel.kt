package com.words.cards.android.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.SettingsEvent
import com.words.cards.presentation.event.SplashEvent
import com.words.cards.presentation.intent.SettingsIntent
import com.words.cards.presentation.intent.SplashIntent
import com.words.cards.presentation.reducer.SettingsReducer
import com.words.cards.presentation.reducer.SplashReducer
import com.words.cards.presentation.state.SettingsScreenContent
import com.words.cards.presentation.state.SplashScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val reducer: SettingsReducer,
) : ViewModel(),
    StateViewModel<SettingsEvent, SettingsScreenContent, SettingsIntent>,
    EventHandle<SplashEvent> {

    override val state: StateFlow<State<SettingsScreenContent, SettingsEvent>> = reducer.state

    override fun onIntent(intent: SettingsIntent) {
        viewModelScope.launch {
            reducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: SplashEvent) {
        viewModelScope.launch {
            reducer.updateEvent(SettingsEvent.Empty)
        }
    }
}