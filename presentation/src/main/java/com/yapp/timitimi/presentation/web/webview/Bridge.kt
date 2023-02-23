package com.yapp.timitimi.presentation.web.webview

import android.webkit.JavascriptInterface

interface Bridge {
    @JavascriptInterface
    fun emptyFunction() = ""

    companion object {
        const val Name = "Android"
    }
}