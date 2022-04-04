package com.lihan.myaccounts.data

fun Account.toAccountEntity() : AccountEntity{
    return AccountEntity(
        icon = icon,
        account = account,
        password = password,
        description = description,
        type = type
    )
}
fun AccountEntity.toAccount() : Account{
    return Account(
        id=id,
        icon = icon,
        account = account,
        password = password,
        description = description,
        type = type
    )
}