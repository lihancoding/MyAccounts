package com.lihan.myaccounts.data

import java.io.FileDescriptor

sealed class AccountInsertEvent{
    data class Account(val account: String) : AccountInsertEvent()
    data class Password(val password: String) : AccountInsertEvent()
    data class Description(val description: String) : AccountInsertEvent()
    data class Icon(val icon: Int) : AccountInsertEvent()
}