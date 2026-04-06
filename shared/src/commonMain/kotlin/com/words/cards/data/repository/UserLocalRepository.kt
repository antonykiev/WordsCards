package com.words.cards.data.repository

import com.words.cards.data.db.entity.UserEntity

interface UserLocalRepository {
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(): Result<UserEntity>
    suspend fun updateUser(user: UserEntity)
    suspend fun deleteUser()
}