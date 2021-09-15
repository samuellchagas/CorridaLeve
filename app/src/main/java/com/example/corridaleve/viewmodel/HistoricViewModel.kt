package com.example.corridaleve.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.corridaleve.model.Historic
import com.example.corridaleve.repository.HistoricRepository
import kotlinx.coroutines.launch

class HistoricViewModel(private val historicRepository: HistoricRepository) : ViewModel(){

    private val _listHistoric = MutableLiveData<List<Historic>>()
    val listHistoric: LiveData<List<Historic>> = _listHistoric

    fun requestListHistoric(){
        viewModelScope.launch {
            _listHistoric.postValue(historicRepository.returnListHistoric())
        }
    }

}