package com.words.cards.android.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.AboutEvent
import com.words.cards.presentation.intent.AboutIntent
import com.words.cards.presentation.reducer.AboutReducer
import com.words.cards.presentation.state.AboutScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AboutViewModel(
    private val aboutReducer: AboutReducer,
) : ViewModel(),
    StateViewModel<AboutEvent, AboutScreenContent, AboutIntent>,
    EventHandle<AboutEvent> {

    override val state: StateFlow<State<AboutScreenContent, AboutEvent>> = aboutReducer.state

    override fun onIntent(intent: AboutIntent) {
        viewModelScope.launch {
            aboutReducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: AboutEvent) {
        viewModelScope.launch {
            aboutReducer.updateEvent(AboutEvent.Empty)
        }
    }
}
