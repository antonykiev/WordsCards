package com.words.cards.android.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.words.cards.presentation.EventHandle
import com.words.cards.presentation.StateViewModel
import com.words.cards.presentation.event.LoginEvent
import com.words.cards.presentation.intent.LoginIntent
import com.words.cards.presentation.reducer.LoginReducer
import com.words.cards.presentation.state.LoginScreenContent
import com.words.cards.presentation.state.State
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginReducer: LoginReducer,
) : ViewModel(),
    StateViewModel<LoginEvent, LoginScreenContent, LoginIntent>,
    EventHandle<LoginEvent> {

    override val state: StateFlow<State<LoginScreenContent, LoginEvent>> = loginReducer.state

    override fun onIntent(intent: LoginIntent) {
        viewModelScope.launch {
            loginReducer.processIntent(intent)
        }
    }

    override fun onEventHandled(event: LoginEvent) {
        viewModelScope.launch {
            loginReducer.updateEvent(LoginEvent.Empty)
        }
    }
}
