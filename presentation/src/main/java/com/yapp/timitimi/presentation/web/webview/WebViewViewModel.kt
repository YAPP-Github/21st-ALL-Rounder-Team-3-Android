package com.yapp.timitimi.presentation.web.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yapp.timitimi.domain.preference.UserPreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class WebViewViewModel(
    private val dataStore: UserPreference
) : ViewModel() {

    protected val _url: MutableStateFlow<String> = MutableStateFlow("")
    val url: StateFlow<String> = _url.asStateFlow()

    private val _accessToken: MutableStateFlow<String> = MutableStateFlow("")
    val accessToken: StateFlow<String> = _accessToken.asStateFlow()

    abstract fun setUrl()

    open fun init() {
        setUrl()
        getAccessToken()
    }

    private fun getAccessToken() = viewModelScope.launch {
        _accessToken.value = dataStore.accessToken
    }
}