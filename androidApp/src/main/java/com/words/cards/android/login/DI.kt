package com.words.cards.android.login

import com.words.cards.presentation.reducer.LoginReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginAndroidModule = module {
    viewModel { LoginViewModel(get<LoginReducer>()) }
}