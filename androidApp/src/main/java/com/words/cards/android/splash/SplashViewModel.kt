package com.words.cards.android.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.SplashEvent
import com.words.cards.presentation.intent.SplashIntent
import com.words.cards.presentation.reducer.SplashReducer
import com.words.cards.presentation.state.SplashScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val reducer: SplashReducer,
) : ViewModel(),
    StateViewModel<SplashEvent, SplashScreenContent, SplashIntent>,
    EventHandle<SplashEvent> {

    override val state: StateFlow<State<SplashScreenContent, SplashEvent>> = reducer.state

    override fun onIntent(intent: SplashIntent) {
        viewModelScope.launch {
            reducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: SplashEvent) {
        viewModelScope.launch {
            reducer.updateEvent(SplashEvent.Empty)
        }
    }
}