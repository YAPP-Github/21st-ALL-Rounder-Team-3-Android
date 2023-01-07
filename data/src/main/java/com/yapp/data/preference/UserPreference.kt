package com.yapp.data.preference

import android.content.Context
import com.yapp.domain.preference.UserPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserPreferenceImpl @Inject constructor(
    @ApplicationContext context: Context,
    applicationId: String,
) : UserPreference {

    private val prefs by lazy {
        context.getSharedPreferences(
            applicationId + PREF_NAME, Context.MODE_PRIVATE
        )
    }

    override fun clear() {
        accessToken = ""
        prefs.edit().remove(ACCESS_TOKEN)
        prefs.edit().clear()
    }

    override var accessToken: String by StringPreference(
        preferences = prefs,
        name = ACCESS_TOKEN,
        defaultValue = ""
    )

    companion object {
        private const val PREF_NAME = "token.pref"
        private const val ACCESS_TOKEN = "accessToken"
    }
}
