package com.lihan.myaccounts.mainui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lihan.myaccounts.data.Account
import com.lihan.myaccounts.data.AccountType
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController
import com.lihan.myaccounts.R
import com.lihan.myaccounts.Resource
import com.lihan.myaccounts.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun AccountListScreen(
    navController: NavHostController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var data by remember {
        mutableStateOf(listOf<Account>())
    }

    LaunchedEffect("",block = {
        viewModel.accounts.collect {
            when(it){
                is Resource.Loading->{}
                is Resource.Success->{ data = it.data }
                is Resource.Fail->{ data = it.data }
            }
        }
    })


    Scaffold(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    navController.navigate(Screen.AccountInsertScreen.route)
                }) {
                Icon(Icons.Filled.Add,"", tint = Color.White)
            }
        }
    ) {

        Column(
            Modifier.fillMaxSize()
        ) {
            LazyColumn{
                items(data){ account->
                    AccountItem(account = account)
                }
            }


        }
    }

}
@Composable
fun AccountItem(
    account : Account,
    viewModel: AccountViewModel = hiltViewModel())
{
    val showDeleteAlert = remember {
        mutableStateOf(false)
    }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                shape = RoundedCornerShape(5.dp),
                backgroundColor = Color.White,
                elevation = 4.dp

            ) {

                Flippable(
                    frontSide = {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Image(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(55.dp),
                                painter = painterResource(id = account.icon),
                                contentDescription = account.description,
                                contentScale = ContentScale.Inside
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(
                                Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "${account.account}",
                                    fontSize = 18.sp,
                                    maxLines = 1,
                                    fontFamily = FontFamily.Monospace
                                )
                                Text(
                                    text = "${account.description}",
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    fontFamily = FontFamily.Monospace
                                )
                            }



                        }
                    },

                    backSide = {
                        val space = rememberCoroutineScope()
                        val passwrod = account.password
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){

                            Text(
                                text = passwrod,
                                fontSize = 18.sp,
                                maxLines = 1,
                                fontFamily = FontFamily.Monospace,
                                modifier = Modifier
                                    .weight(8f)
                                    .padding(start = 24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))

                            Icon(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        showDeleteAlert.value = true
                                    },
                                imageVector = Icons.Filled.Delete, contentDescription = "Delete"
                            )

                            if (showDeleteAlert.value){
                                AlertDialog(
                                    title = { Text(text = "Delete")},
                                    text = { Text(text = "You want delete this account ? ")},
                                    onDismissRequest = {
                                         showDeleteAlert.value = false
                                    },
                                    confirmButton = {
                                        TextButton(onClick = {
                                            viewModel.deleteAccount(account = account)
                                            showDeleteAlert.value = false
                                        }){
                                            Text(text = "Ok")
                                        }
                                                    },
                                    dismissButton = {
                                        TextButton(onClick = { showDeleteAlert.value = false }){
                                            Text(text = "Cancel")
                                        }
                                    }
                                    )
                            }


                            Icon(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {

                                    },
                                imageVector = Icons.Filled.Edit, contentDescription = "Edit")
                        }
                    },

                    onFlippedListener = {

                    },
                    flipController = rememberFlipController()
                )
            }

        }



}






