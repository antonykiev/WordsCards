package com.words.cards.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.words.cards.data.db.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<Result<List<WordEntity>>>

    @Query("SELECT * FROM words WHERE id = :id")
    fun getWordById(id: Long): Result<WordEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity): Result<Long>

    @Update
    suspend fun updateWord(word: WordEntity): Result<Int>

    @Delete
    suspend fun deleteWord(word: WordEntity): Result<Int>
}