package com.lihan.myaccounts.data

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

class AccountNavType  : NavType<Account>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): Account? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Account {
        return Gson().fromJson(value,Account::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Account) {
        bundle.putParcelable(key,value)
    }

}