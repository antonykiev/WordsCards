package com.words.cards.data.datasource

import com.words.cards.data.db.entity.WordEntity
import kotlinx.coroutines.flow.Flow

interface WordDataSource {
    fun getAllWords(): Flow<List<WordEntity>>
    suspend fun getWordById(id: Long): Result<WordEntity?>
    suspend fun insertWord(word: WordEntity): Result<Long>
    suspend fun updateWord(word: WordEntity): Result<Int>
    suspend fun deleteWord(word: WordEntity): Result<Int>
}