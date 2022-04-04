package com.lihan.myaccounts.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AccountEntity::class], version = 1)
abstract class AccountDatabase : RoomDatabase(){
    abstract val dao : AccountDao
}