package com.yapp.timitimi.domain.preference

interface UserPreference {
    var accessToken: String
    var refreshToken: String
    fun clear()
}