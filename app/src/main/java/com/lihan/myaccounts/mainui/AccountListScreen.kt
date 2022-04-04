package com.lihan.myaccounts.mainui

import android.util.Log
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

@Composable
fun AccountListScreen(
    navController: NavHostController,
    viewModel: AccountViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    var data = remember {
        mutableListOf<Account>()
    }
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
                Icon(Icons.Filled.Add,"")
            }
        }
    ) {

        Column(
            Modifier.fillMaxSize()
        ) {
            LazyColumn{
                scope.launch {
                    viewModel.accounts.collect {
                        when(it){
                            is Resource.Loading->{}
                            is Resource.Success->{
                                Log.d("TAG", "AccountListScreen: ")
                               data.addAll(it.data)
                            }
                            is Resource.Fail->{
                                Log.d("TAG", "AccountListScreen:  Fail")
                                data.addAll(it.data)

                            }
                        }
                    }
                }

                items(data){ account->
                    AccountItem(account = account)
                }
            }


        }
    }

}
@Composable
fun AccountItem(account : Account){
//        val colorBackground = when(account.type){
//            AccountType.Mail->{ Mail_Color}
//            AccountType.Bank->{ Bank_Color}
//            AccountType.CreditCard->{ CreditCard_Color}
//            AccountType.Game->{ Game_Color}
//            AccountType.Phone->{ Phone_Color}
//            AccountType.Web->{ Web_Color}
//        }

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
                            Modifier.fillMaxWidth(),
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
                            Text(
                                text = "${account.account}@#$@#$#@#$$$$%REFERFERFEF",
                                fontSize = 18.sp,
                                maxLines = 1,
                                fontFamily = FontFamily.Monospace
                            )

                        }
                    },
                    backSide = {
                        val space = rememberCoroutineScope()
                        var textValue by remember {
                            mutableStateOf("***********************")
                        }
                        var passwrod = account.password
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){

                            Text(
                                text = textValue,
                                fontSize = 18.sp,
                                maxLines = 1,
                                fontFamily = FontFamily.Monospace,
                                modifier = Modifier
                                    .weight(8f)
                                    .padding(start = 24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(55.dp)
                                    .weight(2f)
                                    .clickable {
                                        space.launch {
                                            val temp = textValue
                                            textValue = passwrod
                                            delay(800)
                                            textValue = temp
                                        }
                                    },
                                painter = painterResource(id = R.drawable.ic_baseline_watch_24),
                                contentDescription = "watch",
                                contentScale = ContentScale.Inside
                            )
                        }
                    },
                    flipController = rememberFlipController()
                )
            }

        }



}





