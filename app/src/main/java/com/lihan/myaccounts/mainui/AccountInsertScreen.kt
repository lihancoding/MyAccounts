package com.lihan.myaccounts.mainui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AccountInsertScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello")
        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Cancel")
        }
    }



}