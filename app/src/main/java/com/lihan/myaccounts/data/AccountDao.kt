package com.lihan.myaccounts.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert
    suspend fun insert(accountEntity: AccountEntity)

    @Delete
    suspend fun delete(accountEntity: AccountEntity)

    @Query("select * from AccountEntity")
    fun getAllAccount() : Flow<List<AccountEntity>>
}