package com.words.cards.data.db

expect class DatabaseFactoryAndroid {
    fun createDatabase(): AppDatabase
}