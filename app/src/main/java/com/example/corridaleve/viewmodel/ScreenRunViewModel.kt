package com.example.corridaleve.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.corridaleve.model.Historic
import com.example.corridaleve.repository.ScreenRunRepository
import kotlinx.coroutines.launch

class ScreenRunViewModel(private val screenRunRepository: ScreenRunRepository):ViewModel() {

    fun saveHistoric(historic: Historic) {
        viewModelScope.launch {
            screenRunRepository.saveHistoric(historic)
        }
    }

}