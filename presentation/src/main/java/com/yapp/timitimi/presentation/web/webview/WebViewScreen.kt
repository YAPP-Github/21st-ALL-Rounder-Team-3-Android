package com.yapp.timitimi.presentation.web

import android.webkit.CookieManager
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.yapp.timitimi.presentation.BuildConfig
import com.yapp.timitimi.presentation.web.webview.Bridge
import timber.log.Timber

private const val AccessTokenKey = "access_token"
private const val BaseUrl = BuildConfig.WEB_URL

@Composable
internal fun WebViewScreen(
    urlParam: String,
    accessToken: String,
    bridge: Bridge,
) {
    val url by remember { mutableStateOf("$BaseUrl$urlParam") }
    val webViewClient = remember { AccompanistWebViewClient() }
    val webViewNavigator = rememberWebViewNavigator()
    val webViewState = rememberWebViewState(
        url = url,
        additionalHttpHeaders = mapOf(),
    )

    /*BackHandler {
        if (webViewNavigator.canGoBack) {
            webViewNavigator.navigateBack()
        } else {
            activity.finish()
        }
    }*/
    remember {
        CookieManager.getInstance().apply {
            setAcceptCookie(true)
            removeAllCookies(null)
            setCookie(url, "$AccessTokenKey=$accessToken")
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
                    bridge,
                    Bridge.Name
                )
            }
        },
    )
}