package com.lihan.myaccounts.mainui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lihan.myaccounts.data.AccountInsertEvent
import com.lihan.myaccounts.data.AccountType
import kotlinx.coroutines.launch

@Composable
fun AccountInsertScreen(
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel()
) {

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
                        viewModel.insertAccount()
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
                                        viewModel.inputEvent(AccountInsertEvent.Icon(type.type))
                                        isShow.value = false
                                    }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(spacerWidth))
            Image(
                painter = painterResource(id = viewModel.accountInsertState.icon),
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
                value = viewModel.accountInsertState.account,
                onValueChange ={
                    viewModel.inputEvent(AccountInsertEvent.Account(it))
                }
            )
            Spacer(modifier = Modifier.width(spacerWidth))
            OutlinedTextField(
                label = { Text(text = "Password")},
                value = viewModel.accountInsertState.password,
                onValueChange ={
                    viewModel.inputEvent(AccountInsertEvent.Password(it))

                }
            )
            Spacer(modifier = Modifier.width(spacerWidth))
            OutlinedTextField(
                label = { Text(text = "Description")},
                value = viewModel.accountInsertState.description,
                onValueChange ={
                   viewModel.inputEvent(AccountInsertEvent.Description(it))
                })


        }


    }


}