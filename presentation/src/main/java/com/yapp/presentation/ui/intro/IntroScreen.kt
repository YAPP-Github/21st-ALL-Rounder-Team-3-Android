package com.yapp.presentation.ui.intro

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yapp.presentation.R
import com.yapp.presentation.ui.createproject.CreateProjectActivity
import com.yapp.presentation.ui.login.LoginViewModel

@Composable
fun IntroScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.kakako_login_button),
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .clickable {
                    context.startActivity(
                        Intent(context, CreateProjectActivity::class.java)
                    )
                    (context as? Activity)?.finish()
                },
            contentDescription = "kakao login button",
            tint = Color.Unspecified,
        )
    }
}

@Preview
@Composable
fun IntroPreview() {
    IntroScreen()
}