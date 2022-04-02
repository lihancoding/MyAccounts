package com.lihan.myaccounts.mainui

import android.widget.ImageButton
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lihan.myaccounts.Account
import com.lihan.myaccounts.AccountType
import com.lihan.myaccounts.ui.theme.*
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController
import com.lihan.myaccounts.R
import com.lihan.myaccounts.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AccountListScreen(navController: NavHostController) {
    val dummyDataList = arrayListOf<Account>(
        Account(
            description = "",
            password = "",
            account = "",
            icon = R.drawable.ic_baseline_bank_24,
            type = AccountType.Bank
        ),
        Account(
            description = "",
            password = "",
            account = "",
            icon = R.drawable.ic_baseline_credit_card_24,
            type = AccountType.CreditCard),
       Account(
                description = "",
                password = "",
                account = "",
                icon = R.drawable.ic_baseline_credit_card_24,
                type = AccountType.CreditCard),
        Account(
                    description = "",
                    password = "",
                    account = "",
                    icon = R.drawable.ic_baseline_email_24,
                    type = AccountType.Mail),
        Account(
                    description = "",
                    password = "",
                    account = "",
                    icon = R.drawable.ic_baseline_game_24,
                    type = AccountType.Game),
        Account(
                    description = "",
                    password = "",
                    account = "",
                    icon = R.drawable.ic_baseline_phone_android_24,
                    type = AccountType.Phone),
        Account(
                    description = "",
                    password = "",
                    account = "",
                    icon = R.drawable.ic_baseline_web_24,
                    type = AccountType.Web)


    )

    Scaffold(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
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
                items(dummyDataList){ it->
                    AccountItem(account = it)
                }
                items(dummyDataList){ it->
                    AccountItem(account = it)
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





