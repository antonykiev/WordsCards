package com.words.cards.android.word

import com.words.cards.presentation.reducer.WordReducer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wordModule = module {
    viewModel {
        WordViewModel(
            reducer = get<WordReducer>(),
        )
    }
}