@file:SuppressLint("SetJavaScriptEnabled")

package com.yapp.timitimi.presentation.ui.mytask

import android.annotation.SuppressLint
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.yapp.timitimi.presentation.ui.main.MainActivity
import com.yapp.timitimi.presentation.ui.mytask.webview.MyTaskWebViewBridge
import com.yapp.timitimi.presentation.ui.mytask.webview.MyTaskWebViewClient
import com.yapp.timitimi.ui.startActivityWithAnimation

const val Url = "http://10.0.2.2:8081/project/1/task/1/edit"
const val AccessTokenKey = "access_token" // TODO(EvergreenTree97) : 토큰 넣어야 함
const val AccessTokenValue =
    "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NDRmMWY2Zi1hYzlhLTQ5NzMtYTFmMi02OTdkOGYwMGNiZTUiLCJyb2xlIjoiVVNFUiIsImV4cCI6MTY3NjI1Njg5Mn0.0HMJEXHuOBxON6neyEPtikKMvV4vMSNo_dGsyBfeNao"

@Composable
internal fun MyTaskScreen() {
    val activity = LocalContext.current as MyTaskActivity
    var url by remember { mutableStateOf(Url) }
    val webViewClient = remember {
        MyTaskWebViewClient()
    }
    val webViewNavigator = rememberWebViewNavigator()
    val webViewState = rememberWebViewState(
        url = url,
        additionalHttpHeaders = mapOf(),
    )

    BackHandler {
        if (webViewNavigator.canGoBack) {
            webViewNavigator.navigateBack()
        } else {
            activity.finish()
        }
    }
    remember {
        CookieManager.getInstance().apply {
            setAcceptCookie(true)
            removeAllCookies(null)
            setCookie(Url, "$AccessTokenKey=$AccessTokenValue")
        }
    }
    WebView(
        modifier = Modifier.fillMaxSize(),
        state = webViewState,
        client = webViewClient,
        navigator = webViewNavigator,
        chromeClient = AccompanistWebChromeClient(),
        onCreated = { webView ->
            with(webView) {
                overScrollMode = WebView.OVER_SCROLL_NEVER
                settings.apply {
                    javaScriptEnabled = true
                    setSupportMultipleWindows(true)
                    javaScriptCanOpenWindowsAutomatically = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                }
                addJavascriptInterface(
                    MyTaskWebViewBridge(
                        startMain = {
                            activity.startActivityWithAnimation<MainActivity>()
                        },
                        updateUrl = {
                            url = it
                        }
                    ),
                    MyTaskWebViewBridge.Name
                )
            }
        },
    )
}