package com.tharunbalaji.mad_datastore.utils

object Constants {
    const val PREF_NAME = "userSettings"
    const val APP_ENTRY = "appEntry"

    enum class AuthState {
        LOGGED_IN,
        NOT_LOGGED_IN,
        LOADING
    }
}