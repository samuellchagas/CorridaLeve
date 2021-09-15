package com.example.corridaleve.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.corridaleve.repository.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository):ViewModel() {

    private var email = ""
    private var password = ""

    private val _emailLiveData = MutableLiveData<String>()
    val emailLiveData: LiveData<String> = _emailLiveData

    private val _passwordLiveData = MutableLiveData<String>()
    val passwordLiveData: LiveData<String> = _passwordLiveData

    private val _switchDefaultLiveData = MutableLiveData<Unit>()
    val switchDefaultLiveData:LiveData<Unit> = _switchDefaultLiveData

    fun getEmail(){
        _emailLiveData.postValue(loginRepository.getEmail())
    }

    fun getPassword(){
        _passwordLiveData.postValue(loginRepository.getPassword())
    }

    fun remeberChecked(isChecked: Boolean){
        if(isChecked){
            saveLogin()
        }else{
            deleteLogin()
        }
    }

    private fun saveLogin(){
        loginRepository.saveLogin(email,password)
    }

    private fun deleteLogin(){
        loginRepository.deleteLogin()
    }

    fun switchDefault() {
        if((loginRepository.getEmail().isNotEmpty())&&(loginRepository.getPassword().isNotEmpty())){
            _switchDefaultLiveData.postValue(Unit)
        }
    }

    fun changeEmail(email: String) {
        this.email = email
    }

    fun changePassword(password: String) {
        this.password = password
    }
}
