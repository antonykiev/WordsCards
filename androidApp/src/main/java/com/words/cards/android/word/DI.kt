package com.words.cards.android.word

import com.words.cards.presentation.reducer.NewWordReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val newWordModule = module {
    viewModel {
        NewWordViewModel(
            reducer = get<NewWordReducer>(),
        )
    }
}