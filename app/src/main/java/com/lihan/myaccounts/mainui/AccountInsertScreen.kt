package com.lihan.myaccounts.mainui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lihan.myaccounts.R
import com.lihan.myaccounts.Screen
import com.lihan.myaccounts.data.Account
import com.lihan.myaccounts.data.AccountType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AccountInsertScreen(
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel()
) {

    var textAccount by remember {
        mutableStateOf("")
    }
    var textPassword by remember {
        mutableStateOf("")
    }
    var textDescription by remember {
        mutableStateOf("")
    }
    var accountIcon by remember {
        mutableStateOf(R.drawable.ic_baseline_bank_24)
    }
    val spacerWidth = 16.dp
    val scope = rememberCoroutineScope()
    val typelist = listOf(
        AccountType.Mail,
        AccountType.Bank,
        AccountType.Game,
        AccountType.Web,
        AccountType.CreditCard,
        AccountType.Phone
    )
    var isShow = remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    scope.launch {
                        viewModel.insertAccount(
                            Account(
                                id = 0,
                                icon = accountIcon,
                                account = textAccount,
                                password = textPassword,
                                description =  textDescription,
                                type = accountIcon.toString()
                            )
                        )
                        navController.popBackStack()
                    }
                }) {
                Icon(Icons.Filled.Done,"", tint = Color.White)
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    if (isShow.value){
                    LazyRow{
                        items(typelist){ type->
                            Spacer(modifier = Modifier.width(4.dp))
                            Image(
                                painter = painterResource(id = type.type),
                                contentDescription = type.toString(),
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(8.dp)
                                    .clickable {
                                        accountIcon = type.type
                                    }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(spacerWidth))
            Image(
                painter = painterResource(id = accountIcon),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        isShow.value = !isShow.value
                    }
            )

            Spacer(modifier = Modifier.width(spacerWidth))
            OutlinedTextField(
                label = { Text(text = "Account")},
                value = textAccount,
                onValueChange ={
                    textAccount = it
                }
            )
            Spacer(modifier = Modifier.width(spacerWidth))
            OutlinedTextField(
                label = { Text(text = "Password")},
                value = textPassword,
                onValueChange ={
                        textPassword = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                )
            Spacer(modifier = Modifier.width(spacerWidth))
            OutlinedTextField(
                label = { Text(text = "Description")},
                value = textDescription,
                onValueChange ={
                    textDescription = it
                })


        }


    }


}