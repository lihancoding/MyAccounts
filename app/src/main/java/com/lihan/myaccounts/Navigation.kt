package com.lihan.myaccounts

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lihan.myaccounts.mainui.AccountInsertScreen
import com.lihan.myaccounts.mainui.AccountListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AccountScreen.route ){
        composable(route = Screen.AccountScreen.route){
            AccountListScreen(navController)
        }
        composable(route = Screen.AccountInsertScreen.route){
            AccountInsertScreen(navController)
        }
    }
    
}