package com.lihan.myaccounts.loginui

import android.content.Context
import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lihan.myaccounts.MainActivity
import com.lihan.myaccounts.PINCODE_KEY
import com.lihan.myaccounts.SHAREDPREFERENCES_KEY
import com.lihan.myaccounts.Screen
import java.util.concurrent.Executors

@Composable
fun LoginScreen(navController: NavHostController, mainActivity: MainActivity) {
    val context = LocalContext.current
    var pinCode by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(true) }
    val withFingerLogin = BiometricManager.from(mainActivity)
        .canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS

    if (!withFingerLogin){
        val sp = mainActivity.getSharedPreferences(SHAREDPREFERENCES_KEY, Context.MODE_PRIVATE)
        val mPinCode = sp.getString(PINCODE_KEY,"")
        mPinCode?.let {
            if (it.isEmpty()){
                CreatePinCode(navController)
            }else{
                pinCodeLogin(navController)
            }
        }
    }else{
        fingerLogin(mainActivity,navController)
    }


}

@Composable
fun CreatePinCode(navController: NavHostController) {
    val context = LocalContext.current
    var textFieldValue by remember {
        mutableStateOf("")
    }
    var textFieldValueAgain by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column {
            OutlinedTextField(
                label = { Text(text = "Create PinCode")},
                value = textFieldValue,
                onValueChange = {
                    if (it.length <= 4){
                        textFieldValue = it
                    }
                },
                modifier = Modifier.padding(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                maxLines = 1,
                singleLine = true

            )
            OutlinedTextField(
                label = { Text(text = "Input again")},
                value = textFieldValueAgain,
                onValueChange = {
                    if (it.length <= 4){
                        textFieldValueAgain = it
                    }
                },
                modifier = Modifier.padding(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                maxLines = 1,
                singleLine = true

            )
            Button(
                onClick = {
                    if (textFieldValue == textFieldValueAgain){
                        val sp = context.getSharedPreferences(SHAREDPREFERENCES_KEY, Context.MODE_PRIVATE)
                        sp.edit().putString(PINCODE_KEY,textFieldValue).commit()
                        navController.navigate(Screen.AccountScreen.route)
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp)
            ) {
                Text(text = "Create")
            }
        }


    }
}

@Composable
fun pinCodeLogin(navController: NavHostController) {
    val context = LocalContext.current

    var textFieldValue by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column {
            OutlinedTextField(
                label = { Text(text = "PinCode")},
                value = textFieldValue,
                onValueChange = {
                    if (it.length <= 4){
                        textFieldValue = it
                    }
                },
                modifier = Modifier.padding(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                maxLines = 1,
                singleLine = true

            )
            Button(
                onClick = {
                    val sp = context.getSharedPreferences(SHAREDPREFERENCES_KEY,Context.MODE_PRIVATE)
                    val pincode = sp.getString(PINCODE_KEY,"")
                    if (pincode == textFieldValue){
                        navController.navigate(Screen.AccountScreen.route)
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp)
            ) {
                Text(text = "Login")
            }
        }


    }




}

private fun fingerLogin(mainActivity: MainActivity, navController: NavHostController) {
    when (BiometricManager.from(mainActivity).canAuthenticate()) {
        BiometricManager.BIOMETRIC_SUCCESS -> {

            val prompt = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setSubtitle("Finger Login")
                .setDescription("Please Login")
                .setNegativeButtonText("Cancel")
                .build()

            val executor = Executors.newSingleThreadExecutor()
            val biometricPrompt = BiometricPrompt(
                mainActivity,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        navController.navigate(Screen.AccountScreen.route)
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
            Log.d("fingerLogin", "fingerLogin: BIOMETRIC_ERROR_NONE_ENROLLED")
        }
        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE, BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
            Log.d("fingerLogin", "fingerLogin: BIOMETRIC_ERROR_HW_UNAVAILABLE")

        }
        else -> throw IllegalStateException("ここには入らないはず。")
    }
}


fun isHavePinCode(): Boolean {
    return false
}

