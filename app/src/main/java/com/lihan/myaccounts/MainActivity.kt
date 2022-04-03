package com.lihan.myaccounts


import android.os.Bundle

import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt

import androidx.compose.runtime.*
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executors

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            Navigation(this@MainActivity)
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


