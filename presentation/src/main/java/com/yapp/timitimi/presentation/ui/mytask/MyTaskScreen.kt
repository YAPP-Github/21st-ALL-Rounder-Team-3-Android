@file:SuppressLint("SetJavaScriptEnabled")

package com.yapp.timitimi.presentation.ui.mytask

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.yapp.timitimi.presentation.ui.mytask.webview.MyTaskWebViewBridge
import com.yapp.timitimi.presentation.ui.mytask.webview.MyTaskWebViewClient

@Composable
internal fun MyTaskScreen() {
    val activity = LocalContext.current as MyTaskActivity
    val webViewClient = remember {
        MyTaskWebViewClient()
    }
    val webViewNavigator = rememberWebViewNavigator()
    val webViewState =
        rememberWebViewState(
            url = "http://10.0.2.2:8081/task/create",
            additionalHttpHeaders = emptyMap()
        )

    BackHandler {
        if (webViewNavigator.canGoBack) {
            webViewNavigator.navigateBack()
        } else {
            activity.finish()
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
                    MyTaskWebViewBridge(),
                    MyTaskWebViewBridge.Name
                )
            }
        },
    )
}