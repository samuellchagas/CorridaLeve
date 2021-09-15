package com.example.corridaleve

import android.content.SharedPreferences
import android.provider.Settings.Global.putString
import androidx.core.content.edit

class SharedPreferenceCorrida(private val preference: SharedPreferences) {

    fun getEmail(): String? {
        return preference.getString(EMAIL, "")
    }

    fun getPassword():String? {
        return preference.getString(PASSWORD,"")
    }

    fun saveLogin(email:String, password:String){
        preference.edit{
            putString(EMAIL, email)
            putString(PASSWORD,password)
        }
    }

    fun deleteLogin(){
        preference.edit {
            putString(EMAIL, "")
            putString(PASSWORD,"")
        }
    }

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"

    }
}
