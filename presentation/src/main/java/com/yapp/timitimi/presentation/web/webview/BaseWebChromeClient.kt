package com.yapp.timitimi.presentation.web.webview

import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebChromeClient

class BaseWebChromeClient(
    val onProgressChanged: (Int) -> Unit = {},
) : AccompanistWebChromeClient() {
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        onProgressChanged(newProgress)
        super.onProgressChanged(view, newProgress)
    }
}