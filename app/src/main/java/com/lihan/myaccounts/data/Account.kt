package com.lihan.myaccounts.data



data class Account(
    val id : Int?=null,
    var icon : Int,
    var account : String,
    var password : String,
    var description : String,
    var type : String
)
