package com.yapp.presentation.ui.login.webview

import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient
import timber.log.Timber

class LoginWebViewClient(
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