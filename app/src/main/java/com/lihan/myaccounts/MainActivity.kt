package com.lihan.myaccounts


import android.app.UiModeManager.MODE_NIGHT_NO
import android.os.Bundle
import android.view.Display

import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.lihan.myaccounts.ui.theme.MyAccountsTheme
import com.lihan.myaccounts.ui.theme.Shapes
import java.util.concurrent.Executors

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            LoginScreen()
//            fingerLogin(context)


        }


    }

    @Composable
    private fun fingerLogin(context: MainActivity) {
        when (BiometricManager.from(context).canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                val prompt = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("タイトル。設定必須項目")
                    .setSubtitle("サブタイトル")
                    .setDescription("説明文")
                    .setNegativeButtonText("キャンセルボタン")
                    .build()

                val executor = Executors.newSingleThreadExecutor()
                val biometricPrompt = BiometricPrompt(
                    context,
                    executor,
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            nextScreen()
                        }

                        override fun onAuthenticationError(
                            errorCode: Int,
                            errString: CharSequence
                        ) {
                            // エラーになった場合の処理。ダイアログが閉じる。errorCodeで切り分ける
                        }

                        override fun onAuthenticationFailed() {
                            // 認証に失敗した場合の処理。ダイアログは閉じない。
                        }
                    })
                biometricPrompt.authenticate(prompt)
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                /* 生体情報が端末に登録されていない */
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE, BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {/* 生体認証ハードウェアが利用不可 */
            }
            else -> throw IllegalStateException("ここには入らないはず。")
        }
    }

    private fun nextScreen() {

    }
}

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
                        Toast.makeText(context,"Pin code is $pinCode",Toast.LENGTH_SHORT).show()
                    }
                }) {
                Text(text = "Login")
            }
            }
        }
    }
}
