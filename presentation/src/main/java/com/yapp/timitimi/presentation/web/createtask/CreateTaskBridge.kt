package com.yapp.timitimi.presentation.web.createtask

import android.webkit.JavascriptInterface
import com.yapp.timitimi.presentation.web.webview.Bridge

class CreateTaskBridge(
    val onBackPressed: () -> Unit,
) : Bridge {
    @JavascriptInterface
    fun navigateToMain() {
        onBackPressed()
    }
}