package com.example.bottomnav.util

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {


    private var mySharedPref: SharedPreferences =
        context.getSharedPreferences("filename", Context.MODE_PRIVATE)


    var isLoginFinished: Boolean
        get() = mySharedPref.getBoolean(CONSTANTS.IS_LOGIN_FINISHED, false)
        set(value) {
            mySharedPref.edit().putBoolean(CONSTANTS.IS_LOGIN_FINISHED, value).apply()
        }

    var locations: String
        get() = mySharedPref.getString("lnlt", "")!!
        set(value) {
            mySharedPref.edit().putString("lnlt", value).apply()
        }

    var USER_ID: String
        get() = mySharedPref.getString(CONSTANTS.USER_ID, "")!!
        set(value) {
            mySharedPref.edit().putString(CONSTANTS.USER_ID, value).apply()
        }

    var userJson: String
        get() = mySharedPref.getString(CONSTANTS.USER_JSON, "")!!
        set(value) {
            mySharedPref.edit().putString(CONSTANTS.USER_JSON, value).apply()
        }

    var isFirst: Boolean
        get() = mySharedPref.getBoolean(CONSTANTS.IS_FIRST, false)
        set(value) {
            mySharedPref.edit().putBoolean(CONSTANTS.IS_FIRST, value).apply()
        }

    var token: String
        get() = mySharedPref.getString(CONSTANTS.TOKEN, "")!!
        set(value) {
            mySharedPref.edit().putString(CONSTANTS.TOKEN, value).apply()
        }

}