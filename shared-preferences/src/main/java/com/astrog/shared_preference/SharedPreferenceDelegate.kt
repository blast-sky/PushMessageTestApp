package com.astrog.shared_preference

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty


interface SharedPreferenceDelegate<T> {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)

    class String(
        private val sharedPreferences: SharedPreferences,
        private val name: kotlin.String,
        private val defaultValue: kotlin.String = ""
    ) : SharedPreferenceDelegate<kotlin.String> {

        override fun getValue(thisRef: Any?, property: KProperty<*>): kotlin.String =
            sharedPreferences.getString(name, defaultValue)!!


        override fun setValue(thisRef: Any?, property: KProperty<*>, value: kotlin.String) =
            sharedPreferences.edit { putString(name, value) }
    }

    class Boolean(
        private val sharedPreferences: SharedPreferences,
        private val name: kotlin.String,
        private val defaultValue: kotlin.Boolean = false
    ) : SharedPreferenceDelegate<kotlin.Boolean> {

        override fun getValue(thisRef: Any?, property: KProperty<*>): kotlin.Boolean =
            sharedPreferences.getBoolean(name, defaultValue)


        override fun setValue(thisRef: Any?, property: KProperty<*>, value: kotlin.Boolean) {
            sharedPreferences.edit { putBoolean(name, value) }
        }
    }
}

