package com.words.cards.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.words.cards.data.db.dao.WordDao
import com.words.cards.data.db.entity.WordEntity

@Database(
    entities = [
        WordEntity::class
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}
