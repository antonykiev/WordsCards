package com.words.cards.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 1,
    @ColumnInfo(name = "learned_language") val learnedLanguageId: Int,
    @ColumnInfo(name = "original_language") val originalLanguageId: Int,

    @ColumnInfo(name = "show_transcription") val showTranscription: Boolean,
    @ColumnInfo(name = "show_description") val showDescription: Boolean,
    @ColumnInfo(name = "show_translation") val showTranslation: Boolean,
    @ColumnInfo(name = "show_example") val showExample: Boolean,
)
