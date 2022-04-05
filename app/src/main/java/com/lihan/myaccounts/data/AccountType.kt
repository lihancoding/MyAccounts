package com.lihan.myaccounts.data

import com.lihan.myaccounts.R


sealed class AccountType(val type : Int ){
    object Mail : AccountType(R.drawable.ic_baseline_email_24)
    object Bank : AccountType(R.drawable.ic_baseline_bank_24)
    object CreditCard : AccountType(R.drawable.ic_baseline_credit_card_24)
    object Game : AccountType(R.drawable.ic_baseline_game_24)
    object Phone : AccountType(R.drawable.ic_baseline_phone_android_24)
    object Web : AccountType(R.drawable.ic_baseline_web_24)
}
