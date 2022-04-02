package com.lihan.myaccounts.loginui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lihan.myaccounts.ui.theme.MyAccountsTheme

@Composable
fun LoginScreen(){
    val context = LocalContext.current
    var pinCode  by remember{ mutableStateOf("") }
    MyAccountsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ){

            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier.padding(32.dp),
                    text = "PIN Code",
                    fontSize = 44.sp
                )

                OutlinedTextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        cursorColor = MaterialTheme.colors.primary,
                        disabledLabelColor = Color.Black,
                        focusedIndicatorColor = MaterialTheme.colors.primary,
                        unfocusedIndicatorColor = MaterialTheme.colors.primary
                    ),
                    maxLines = 1,
                    singleLine = true,
                    shape = RoundedCornerShape(4.dp),
                    value = pinCode,
                    onValueChange = {
                        pinCode = it
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)

                )
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        if (pinCode.isNotEmpty()){
                            Toast.makeText(context,"Pin code is $pinCode", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                    Text(text = "Login")
                }
            }
        }
    }
}

