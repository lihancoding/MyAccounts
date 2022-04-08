package com.lihan.myaccounts

sealed class Screen(val route : String ){
    object LoginScreen : Screen("login_screen")
    object AccountScreen : Screen("account_screen")
    object AccountInsertScreen : Screen("account_insert_screen")
    object AccountUpdateScreen : Screen("account_update_screen")
}
