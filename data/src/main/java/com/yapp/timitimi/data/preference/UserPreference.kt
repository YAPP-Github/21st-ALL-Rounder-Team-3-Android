package com.yapp.timitimi.data.preference

import android.content.Context
import androidx.core.content.edit
import com.yapp.timitimi.domain.preference.UserPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
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
        prefs.edit().remove(REFRESH_TOKEN)
        prefs.edit().clear()
    }

    override var accessToken: String by StringPreference(
        preferences = prefs,
        name = ACCESS_TOKEN,
        defaultValue = ""
    )

    override var refreshToken: String by StringPreference(
        preferences = prefs,
        name = REFRESH_TOKEN,
        defaultValue = ""
    )

    override fun getIsFirstProject(): Flow<Boolean> =
        flow { prefs.getBoolean(IS_FIRST_PROJECT, true) }

    override suspend fun setIsFirstProject(isFirstProject: Boolean) = withContext(Dispatchers.IO) {
        prefs.edit {
            putBoolean(IS_FIRST_PROJECT, isFirstProject)
        }
    }


    companion object {
        private const val PREF_NAME = "token.pref"
        private const val ACCESS_TOKEN = "accessToken"
        private const val REFRESH_TOKEN = "refreshToken"
        private const val IS_FIRST_PROJECT = "isFirstProject"
    }
}
