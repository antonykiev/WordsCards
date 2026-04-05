package com.words.cards.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 1,
    @ColumnInfo(name = "learned_language") val learnedLanguage: String,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
)
