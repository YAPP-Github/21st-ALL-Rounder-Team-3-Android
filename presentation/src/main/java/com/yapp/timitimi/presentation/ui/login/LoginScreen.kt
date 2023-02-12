package com.yapp.timitimi.presentation.ui.login

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.yapp.timitimi.presentation.BuildConfig
import com.yapp.timitimi.presentation.ui.login.redux.LoginIntent
import com.yapp.timitimi.presentation.ui.login.redux.LoginSingleEvent
import com.yapp.timitimi.presentation.ui.login.webview.LoginWebViewClient
import com.yapp.timitimi.presentation.ui.onboarding.OnboardingActivity
import com.yapp.timitimi.ui.startActivityWithAnimation
import kotlinx.coroutines.flow.launchIn
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
                    is LoginSingleEvent.ShowToast -> {
                        Toast.makeText(context, event.msg , Toast.LENGTH_SHORT).show()
                    }

                    LoginSingleEvent.Finish -> {
                        (context as Activity).finish()
                    }

                    LoginSingleEvent.NavigateToCreateProject -> {
                        (context as Activity).startActivityWithAnimation<OnboardingActivity>(
                            withFinish = true
                        )
                    }
                }
            }
            .launchIn(this)
    }

    val webViewClient = remember {
        LoginWebViewClient(
            onLoginSucceed = { appToken, refreshToken ->
                viewModel.dispatch(LoginIntent.KakaoLoginSucceed(appToken, refreshToken))
            },
            onLoginFailed = {
                viewModel.dispatch(LoginIntent.KakaoLoginFailed)
            }
        )
    }

    val webViewState =
        rememberWebViewState(
            url = BuildConfig.KAKAO_LOGIN_AUTH_URL,
            additionalHttpHeaders = emptyMap()
        )

    WebView(
        modifier = Modifier
            .fillMaxSize(),
        state = webViewState,
        client = webViewClient,
        chromeClient = AccompanistWebChromeClient(),
        onCreated = { webView ->
            webView.settings.apply {
                javaScriptEnabled = true
                setSupportMultipleWindows(true)
                javaScriptCanOpenWindowsAutomatically = true
                loadWithOverviewMode = true
                useWideViewPort = true
                domStorageEnabled = true
            }
        },
    )
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen()
}
