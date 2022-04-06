package com.lihan.myaccounts.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(accountEntity: AccountEntity)

    @Delete
    suspend fun delete(accountEntity: AccountEntity)

    @Query("select * from AccountEntity ORDER by type")
    fun getAllAccount() : Flow<List<AccountEntity>>
}