package com.lihan.myaccounts.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun insert(account: Account)
    suspend fun delete(account: Account)
    fun getAllAccount() : Flow<List<Account>>
}