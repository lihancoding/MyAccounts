package com.lihan.myaccounts

import com.lihan.myaccounts.data.Account

sealed class Resource{
    object Loading : Resource()
    data class Success(val data : List<Account>) : Resource()
    data class Fail(val data : List<Account> = arrayListOf() , val message : String) : Resource()
}