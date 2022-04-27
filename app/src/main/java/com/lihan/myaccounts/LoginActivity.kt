package com.lihan.myaccounts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.lihan.myaccounts.ui.theme.MyAccountsTheme

class LoginActivity : FragmentActivity() {

    private lateinit var biometricPrompt : BiometricPrompt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val executors = ContextCompat.getMainExecutor(this)
            val callback = object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    finish()
                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            }
            val biometricManager = BiometricManager.from(this)
            when (biometricManager.canAuthenticate()) {
                BiometricManager.BIOMETRIC_SUCCESS ->
                    Log.d("TAG", "App can authenticate using biometrics.")
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                    Log.e("TAG", "No biometric features available on this device.")
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    Log.e("TAG", "Biometric features are currently unavailable.")
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->
                    Log.e("TAG", "The user hasn't associated " +
                            "any biometric credentials with their account.")
            }

            val biometricPromptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Authentication")
                    .setDescription("")
                    .setDeviceCredentialAllowed(true)
                    .build()

            biometricPrompt = BiometricPrompt(this, executors, callback)
            biometricPrompt.authenticate(biometricPromptInfo)

            MyAccountsTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        biometricPrompt.authenticate(biometricPromptInfo)
                    }) {
                        Text(text = "Login")
                    }
                }
            }

        }
    }
}