package com.lihan.myaccounts

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.lihan.myaccounts.data.Account
import com.lihan.myaccounts.mainui.AccountInsertScreen
import com.lihan.myaccounts.mainui.AccountListScreen
import com.lihan.myaccounts.mainui.AccountUpdateScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AccountListScreen.route ){
        composable(route = Screen.AccountListScreen.route){
            AccountListScreen(navController)
        }

        composable(route = Screen.AccountInsertScreen.route){
            AccountInsertScreen(navController = navController)
        }
        composable(
            route = Screen.AccountUpdateScreen.route +"/{account}",
            arguments = listOf(
                navArgument("account"){
                type= NavType.StringType
                }),
            ) {
            it.arguments?.getString("account")?.let { json->
                val account = Gson().fromJson(json,Account::class.java)
                AccountUpdateScreen(account = account, navController = navController)
            }

        }



    }

}