package com.words.cards.android.splash

import com.words.cards.presentation.reducer.SplashReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel(get<SplashReducer>()) }
}