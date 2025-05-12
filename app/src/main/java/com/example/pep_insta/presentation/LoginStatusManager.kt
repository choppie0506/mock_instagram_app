package com.example.pep_insta.presentation

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object LoginStatusManager {
    private const val PREFS_NAME = "secure_prefs"
    private const val KEY_LOGGED_IN = "is_logged_in"

    private fun getPrefs(context: Context) = EncryptedSharedPreferences.create(
        context,
        PREFS_NAME,
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun isLoggedIn(context: Context): Boolean =
        getPrefs(context).getBoolean(KEY_LOGGED_IN, false)

    fun setLoggedIn(context: Context, value: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_LOGGED_IN, value).apply()
    }
} 