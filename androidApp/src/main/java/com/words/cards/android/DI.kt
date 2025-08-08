package com.words.cards.android

import com.words.cards.resource.ResourceProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single {
        ResourceProvider(
            context = androidContext()
        )
    }
}