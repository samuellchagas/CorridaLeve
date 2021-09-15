package com.example.corridaleve.repository

import com.example.corridaleve.SharedPreferenceCorrida

class LoginRepository(private val sharedPreference: SharedPreferenceCorrida) {

    fun saveLogin(email:String, password:String){
        sharedPreference.saveLogin(email,password)
    }

    fun getEmail():String {
        return sharedPreference.getEmail()!!
    }

    fun getPassword():String {
        return sharedPreference.getPassword()!!
    }

    fun deleteLogin(){
        sharedPreference.deleteLogin()
    }
}