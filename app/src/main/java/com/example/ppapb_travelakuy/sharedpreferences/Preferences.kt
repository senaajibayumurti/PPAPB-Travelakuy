package com.example.ppapb_travelakuy.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class Preferences private constructor(context: Context) {

    private val preferences: SharedPreferences
    companion object {
        private const val PREF_FILE_NAME = "session"
        private const val IS_LOGGED_IN = "is_logged_in"
        private const val IS_ADMIN = "is_admin"
        private const val USERNAME = "username"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val ID = "id"

        @Volatile
        private var instance: Preferences? = null
        fun getInstance(context: Context): Preferences {
            return instance ?: synchronized(this) {
                instance ?: Preferences(context.applicationContext).also { instance = it }
            }
        }
    }
    init {
        preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun setLoggedIn(isLoggedIn: Boolean, isAdmin: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn)
        editor.putBoolean(IS_ADMIN, isAdmin)
        editor.apply()
    }
    fun isLoggedIn(): Boolean {
        return preferences.getBoolean(IS_LOGGED_IN, false)
    }
    fun isAdmin(): Boolean {
        return preferences.getBoolean(IS_ADMIN, false)
    }

    fun setId(id: String) {
        val editor = preferences.edit()
        editor.putString(ID, id)
        editor.apply()
    }
    fun getId(): String {
        return preferences.getString(ID, "")!!
    }

    fun setAdmin() {
        val editor = preferences.edit()
        editor.putBoolean(IS_ADMIN, true)
        editor.apply()
    }

    fun setNotAdmin() {
        val editor = preferences.edit()
        editor.putBoolean(IS_ADMIN, false)
        editor.apply()
    }

    fun saveUsername(username: String) {
        val editor = preferences.edit()
        editor.putString(USERNAME, username)
        editor.apply()
    }

    fun getUsername(): String {
        return preferences.getString(USERNAME, "")!!
    }

    fun savePassword(password: String) {
        val editor = preferences.edit()
        editor.putString(PASSWORD, password)
        editor.apply()
    }
    fun getPassword(): String {
        return preferences.getString(PASSWORD, "")!!
    }

    fun saveEmail(email: String) {
        val editor = preferences.edit()
        editor.putString(EMAIL, email)
        editor.apply()
    }
    fun getEmail(): String {
        return preferences.getString(EMAIL, "")!!
    }
    fun clear() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}