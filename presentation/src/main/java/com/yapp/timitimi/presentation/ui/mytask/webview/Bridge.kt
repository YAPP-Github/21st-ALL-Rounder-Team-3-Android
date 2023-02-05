package com.yapp.timitimi.presentation.ui.mytask.webview

import android.webkit.JavascriptInterface

class MyTaskWebViewBridge(){

    @JavascriptInterface
    fun showMessage(){

    }

    companion object{
        const val Name = "Native"
    }
}