package com.example.corridaleve.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.corridaleve.model.User

class RegisterViewModel:ViewModel() {

    private val _validUserLiveData = MutableLiveData <User>()
    val validUserLiveData: LiveData<User> = _validUserLiveData

    private val _errorLiveData = MutableLiveData <String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    fun validLogin(email:String, password:String,confirmPassword:String){

        if(email.isEmpty()||password.isEmpty()||password!=confirmPassword||email.contains(" ")){
            _errorLiveData.postValue("Preencha os campos corretamente!")
        }else{
            _validUserLiveData.postValue(User(email,password))
        }
    }
}
