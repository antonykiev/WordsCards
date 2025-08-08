package com.words.cards.data.repository

import com.words.cards.data.db.entity.WordEntity
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun getAllWords(): Flow<Result<List<WordEntity>>>
    suspend fun getWordById(id: Long): Result<WordEntity?>
    suspend fun insertWord(wordEntity: WordEntity): Result<Long>
    suspend fun updateWord(word: WordEntity): Result<Int>
    suspend fun deleteWord(word: WordEntity): Result<Int>
}