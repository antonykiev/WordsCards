package com.words.cards.domain.entity

data class WordDomain(
    val id: Long = 0,
    val wordText: String,
    val wordDescription: String,
    val wordTranslation: String,
    val wordTranscription: String,
    val wordExamples: String,
    val createdAt: Long,
)