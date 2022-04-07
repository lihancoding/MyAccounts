package com.lihan.myaccounts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntity(
    var icon : Int,
    var account : String,
    var password : String,
    var description : String,
    var type : String,
    @PrimaryKey(autoGenerate = true) var id : Int?=null
)