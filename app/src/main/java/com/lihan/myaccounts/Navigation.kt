package com.lihan.myaccounts

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lihan.myaccounts.data.Account
import com.lihan.myaccounts.loginui.LoginScreen
import com.lihan.myaccounts.mainui.AccountInsertScreen
import com.lihan.myaccounts.mainui.AccountListScreen
import com.lihan.myaccounts.mainui.AccountUpdateScreen
import com.lihan.myaccounts.mainui.AccountViewModel

@Composable
fun Navigation(mainActivity: MainActivity) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AccountScreen.route ){
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController,mainActivity)
        }
        composable(route = Screen.AccountScreen.route){
            AccountListScreen(navController)
        }
        composable(arguments = listOf(navArgument("account"){type= NavType.ParcelableType(Account::class.java)}),route = Screen.AccountInsertScreen.route){
            AccountInsertScreen(navController)
        }

    }
    
}