package com.yapp.timitimi.data.preference

import android.content.SharedPreferences
import androidx.annotation.AnyThread
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class StringPreference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defaultValue: String,
    private val setValueAction: (() -> Unit)? = null
) : ReadWriteProperty<Any, String?> {

    @AnyThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preferences.getString(name, defaultValue) ?: ""
    }

    @AnyThread
    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.edit {
            putString(name, value)
            setValueAction?.invoke()
        }
    }
}