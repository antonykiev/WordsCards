package com.words.cards.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.words.cards.data.db.dao.LanguageDao
import com.words.cards.data.db.dao.SettingsDao
import com.words.cards.data.db.dao.UserDao
import com.words.cards.data.db.dao.WordDao
import com.words.cards.data.db.entity.LanguageEntity
import com.words.cards.data.db.entity.SettingsEntity
import com.words.cards.data.db.entity.UserEntity
import com.words.cards.data.db.entity.WordEntity

@Database(
    entities = [
        WordEntity::class,
        UserEntity::class,
        SettingsEntity::class,
        LanguageEntity::class
    ],
    version = 2,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun userDao(): UserDao
    abstract fun settingsDao(): SettingsDao
    abstract fun languageDao(): LanguageDao
}
