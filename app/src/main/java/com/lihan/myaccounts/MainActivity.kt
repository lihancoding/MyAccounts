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
import com.lihan.myaccounts.loginui.LoginScreen
import com.lihan.myaccounts.mainui.AccountListScreen
import com.lihan.myaccounts.ui.theme.MyAccountsTheme
import com.lihan.myaccounts.ui.theme.Shapes
import java.util.concurrent.Executors

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            Navigation()
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


