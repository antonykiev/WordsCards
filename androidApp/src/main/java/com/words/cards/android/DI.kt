package com.words.cards.android

import androidx.room.Room
import com.words.cards.data.db.AppDatabase
import com.words.cards.resource.AssetReader
import com.words.cards.resource.ResourceProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val androidModule = module {
    single {
        ResourceProvider(
            context = androidContext()
        )
    }
    single {
        AssetReader(
            assetManager = androidContext().assets
        )
    }
    single<AppDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = "words_cards_database",
        ).build()
    } bind AppDatabase::class
}