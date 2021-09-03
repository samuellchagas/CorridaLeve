package com.example.corridaleve

import android.content.SharedPreferences
import android.provider.Settings.Global.putString
import androidx.core.content.edit

class SharedPreferenceCorrida(private val preference: SharedPreferences) {

    fun getEmail(): String? {
        return preference.getString("email", "")
    }

    fun getPassword():String? {
        return preference.getString("password","")
    }

    fun saveLogin(email:String, password:String){
        preference.edit{
            putString("email", email)
            putString("password",password)
        }
    }

    fun deleteLogin(){
        preference.edit {
            putString("email", "")
            putString("password","")
        }
    }
}