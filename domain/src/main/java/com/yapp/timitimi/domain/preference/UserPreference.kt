package com.yapp.timitimi.domain.preference

interface UserPreference {
    var accessToken: String
    fun clear()
}