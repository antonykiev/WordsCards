package com.words.cards.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "word_text") val wordText: String,
    @ColumnInfo(name = "word_description") val wordDescription: String,
    @ColumnInfo(name = "word_translation") val wordTranslation: String,
    @ColumnInfo(name = "word_transcription") val wordTranscription: String,
    @ColumnInfo(name = "word_examples") val wordExamples: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
)