package com.yapp.timitimi.presentation.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yapp.timitimi.theme.AllRounder3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllRounder3Theme {
                LoginScreen()
            }
        }
    }
}
//
//@AndroidEntryPoint
//class LoginActivity : AppCompatActivity() {
//    private val viewModel: LoginViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//        initWebView()
//        observeEvent()
//    }
//
//    private fun initWebView() {
//        val webView = findViewById<WebView>(R.id.webView)
//
//        webView.webViewClient = LoginWebViewClient(
//            onLoginSucceed = { appToken ->
//                viewModel.dispatch(LoginIntent.KakaoLoginSucceed(appToken))
//            },
//            onLoginFailed = {
//                viewModel.dispatch(LoginIntent.KakaoLoginFailed)
//            }
//        )
//
//        webView.settings.apply {
//            javaScriptEnabled = true
//            setSupportMultipleWindows(true)
//            javaScriptCanOpenWindowsAutomatically = true
//            loadWithOverviewMode = true
//            useWideViewPort = true
//            domStorageEnabled = true
//        }
//
//        webView.loadUrl(BuildConfig.KAKAO_LOGIN_AUTH_URL)
//    }
//
//    private fun observeEvent() {
//        viewModel.singleEventFlow
//            .onEach { event ->
//                when (event) {
//                    LoginSingleEvent.ShowToast -> {
//                        Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
//                    }
//
//                    LoginSingleEvent.NavigateToCreateProject -> {
//                        startActivity(Intent(this, IntroActivity::class.java))
//                        finish()
//                    }
//                }
//            }
//    }
//}