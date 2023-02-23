package com.yapp.timitimi.presentation.ui.login.webview

import android.net.Uri
import android.webkit.CookieManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient
import timber.log.Timber

class LoginWebViewClient(
    private val onLoginSucceed: (String, String) -> Unit,
    private val onLoginFailed: () -> Unit
) : AccompanistWebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        super.shouldOverrideUrlLoading(view, request)
        Timber.e(request?.url.toString())
        if (isLoginSucceed(request?.url)) return false
        return false
    }

    private fun isLoginSucceed(url: Uri?): Boolean {
        if (url?.queryParameterNames?.contains(QUERY_PARAMS_APP_TOKEN) == true) {
            // 카카오 로그인 실패했을 때 appToken 을 empty string 으로 내려준다.
            val appToken = url.getQueryParameter(QUERY_PARAMS_APP_TOKEN) ?: ""
            if (appToken.isNotBlank()) {
                CookieManager.getInstance().apply {
                    setAcceptCookie(true)
                    val cookies = getCookie(url.toString())
                    val refreshToken = getRefreshToken(cookies)
                    onLoginSucceed(appToken, refreshToken)
                }
            } else {
                onLoginFailed()
            }
        }
        return false
    }

    private fun getRefreshToken(cookies: String): String {
        val pairs = mutableMapOf<String, String>()
        cookies.replace(" ", "").split(";").forEach {
            val parts = it.split("=")
            pairs[parts[0]] = parts[1]
        }
        Timber.e("refresh token is", pairs.getOrElse("refreshToken") { "" })

        return pairs.getOrElse("refreshToken") { "" }
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