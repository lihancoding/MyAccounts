package com.lihan.myaccounts

data class Account(
    var icon : Int,
    var account : String,
    var password : String,
    var description : String,
    var type : AccountType
)

sealed class AccountType{
    object Mail : AccountType()
    object Bank : AccountType()
    object CreditCard : AccountType()
    object Game : AccountType()
    object Phone : AccountType()
    object Web : AccountType()
}