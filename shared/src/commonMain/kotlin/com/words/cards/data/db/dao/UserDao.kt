package com.words.cards.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.words.cards.data.db.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserEntity)

    @Query("SELECT * FROM user WHERE id = 1")
    fun getUser(): UserEntity?

    @Update
    suspend fun updateUser(user: UserEntity): Int

    @Query("DELETE FROM user WHERE id = 1")
    suspend fun deleteUser(): Int
}