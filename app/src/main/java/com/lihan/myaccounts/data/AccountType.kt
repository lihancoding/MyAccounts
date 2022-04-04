package com.lihan.myaccounts.data


sealed class AccountType{
    object Mail : AccountType()
    object Bank : AccountType()
    object CreditCard : AccountType()
    object Game : AccountType()
    object Phone : AccountType()
    object Web : AccountType()
}
