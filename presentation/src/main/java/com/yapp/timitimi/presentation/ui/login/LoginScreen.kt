package com.yapp.timitimi.presentation.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.yapp.timitimi.presentation.BuildConfig
import com.yapp.timitimi.presentation.ui.intro.IntroActivity
import com.yapp.timitimi.presentation.ui.login.redux.LoginIntent
import com.yapp.timitimi.presentation.ui.login.redux.LoginSingleEvent
import com.yapp.timitimi.presentation.ui.login.webview.LoginWebViewClient
import kotlinx.coroutines.flow.onEach

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {
    LaunchedEffect(viewModel.singleEventFlow) {
        viewModel.singleEventFlow
            .onEach { event ->
                when (event) {
                    LoginSingleEvent.ShowToast -> {
                        Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }

                    LoginSingleEvent.NavigateToCreateProject -> {
                        context.startActivity(Intent(context, IntroActivity::class.java))
                        (context as? Activity)?.finish()
                    }
                }
            }
    }

    val webViewClient = LoginWebViewClient(
        onLoginSucceed = { appToken ->
            viewModel.dispatch(LoginIntent.KakaoLoginSucceed(appToken))
        },
        onLoginFailed = {
            viewModel.dispatch(LoginIntent.KakaoLoginFailed)
        }
    )
    val webChromeClient = AccompanistWebChromeClient()

    val webViewState =
        rememberWebViewState(
            url = BuildConfig.KAKAO_LOGIN_AUTH_URL,
            additionalHttpHeaders = emptyMap()
        )

    WebView(
        state = webViewState,
        client = webViewClient,
        chromeClient = webChromeClient
    )
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen()
}