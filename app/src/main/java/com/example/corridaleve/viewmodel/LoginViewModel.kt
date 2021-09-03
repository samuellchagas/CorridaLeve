package com.example.corridaleve.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.corridaleve.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository):ViewModel() {

    private val _emailLiveData = MutableLiveData<String>()
    val emailLiveData: LiveData<String> = _emailLiveData

    private val _passwordLiveData = MutableLiveData<String>()
    val passwordLiveData: LiveData<String> = _passwordLiveData

    private val _rememberLogin = MutableLiveData<Unit>()
    val rememberLogin:LiveData<Unit> = _rememberLogin

    private var onChecked:Boolean = false

    fun getEmail(){
        _emailLiveData.postValue(loginRepository.getEmail())
    }

    fun getPassword(){
        _passwordLiveData.postValue(loginRepository.getPassword())
    }

//    fun remeberChecked(isChecked: Boolean){
//        if(isChecked){
//            this.onChecked = true
//        }else{
//            this.onChecked = false
//        }
//    }

    fun saveLogin(email:String, password:String){
        loginRepository.saveLogin(email,password)
    }


}
