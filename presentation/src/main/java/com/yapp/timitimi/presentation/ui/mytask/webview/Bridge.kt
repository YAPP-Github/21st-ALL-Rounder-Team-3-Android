package com.yapp.timitimi.presentation.ui.mytask.webview

import android.webkit.JavascriptInterface
import timber.log.Timber

class MyTaskWebViewBridge() {

    @JavascriptInterface
    fun showToast(text: String) {
        Timber.e(text)
    }

    companion object {
        const val Name = "Android"
    }
}