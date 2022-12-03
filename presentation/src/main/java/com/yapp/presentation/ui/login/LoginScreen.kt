package com.yapp.presentation.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.yapp.presentation.ui.createproject.CreateProjectActivity

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            context.startActivity(
                Intent(context, CreateProjectActivity::class.java)
            )
            (context as? Activity)?.finish()
        }) {
            Text(text = "카카오로 로그인 하기")
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen()
}