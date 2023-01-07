package com.yapp.presentation.ui.task

import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.yapp.presentation.BuildConfig
import com.yapp.presentation.ui.login.redux.LoginIntent
import com.yapp.presentation.ui.login.webview.LoginWebViewClient
import timber.log.Timber

class CheckWebViewClient(
    private val onLoginSucceed: (String) -> Unit,
    private val onLoginFailed: () -> Unit
): AccompanistWebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        super.shouldOverrideUrlLoading(view, request)
        Timber.e("Success Redirect")
        parseUrl(request?.url)
        return true
    }

    private fun parseUrl(url: Uri?) {
        if (url?.queryParameterNames?.contains(QUERY_PARAMS_APP_TOKEN) == true) {
            onLoginSucceed(url.getQueryParameter(QUERY_PARAMS_APP_TOKEN) ?: "")
        } else {
            onLoginFailed()
        }
    }


    companion object {
        private const val QUERY_PARAMS_APP_TOKEN = "appToken"
    }
}

@Composable
internal fun CheckTaskScreen(){
    val webViewClient = CheckWebViewClient(
        onLoginSucceed = { appToken ->
        },
        onLoginFailed = {

        }
    )
    TimiWebView(
        url = "http://10.0.2.2:8081/task/123",
        webViewClient = webViewClient,
    )
}

@Composable
internal fun TimiWebView(
    url: String,
    webViewClient: AccompanistWebViewClient
){
    val webChromeClient = AccompanistWebChromeClient()
    val webViewState =
        rememberWebViewState(
            url = url,
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
fun test(){
    CheckTaskScreen()
}