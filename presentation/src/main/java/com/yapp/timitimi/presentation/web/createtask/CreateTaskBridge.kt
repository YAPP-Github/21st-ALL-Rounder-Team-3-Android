package com.yapp.timitimi.presentation.web.createtask

import android.webkit.JavascriptInterface
import com.yapp.timitimi.presentation.web.webview.Bridge

interface CreateTaskBridge : Bridge {
    @JavascriptInterface
    fun navigateToMain()
}