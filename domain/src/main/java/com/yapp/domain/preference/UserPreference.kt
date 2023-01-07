package com.yapp.domain.preference

interface UserPreference {
    var accessToken: String
    fun clear()
}