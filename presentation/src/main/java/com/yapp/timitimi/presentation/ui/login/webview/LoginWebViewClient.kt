package com.yapp.timitimi.presentation.ui.login.webview

import android.net.Uri
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient
import timber.log.Timber

class LoginWebViewClient(
    private val onLoginSucceed: (String) -> Unit,
    private val onLoginFailed: () -> Unit
) : AccompanistWebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        super.shouldOverrideUrlLoading(view, request)
        Timber.e(request?.url?.toString())
       // parseUrl(request?.url)
        view?.loadUrl(request?.url.toString())
        return false
    }

    private fun parseUrl(url: Uri?) {
        if (url?.queryParameterNames?.contains(QUERY_PARAMS_APP_TOKEN) == true) {
            onLoginSucceed(url.getQueryParameter(QUERY_PARAMS_APP_TOKEN) ?: "")
        } else {
         //   onLoginFailed()
        }
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        onLoginFailed()
    }

    companion object {
        private const val QUERY_PARAMS_APP_TOKEN = "appToken"
    }
}