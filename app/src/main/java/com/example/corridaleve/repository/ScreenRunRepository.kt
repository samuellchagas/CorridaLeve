package com.example.corridaleve.repository

import com.example.corridaleve.data.DAOs.HistoricDAO
import com.example.corridaleve.model.Historic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScreenRunRepository(private val historicDAO: HistoricDAO) {

    suspend fun saveHistoric(historic: Historic) {
        withContext(Dispatchers.IO) {
            historicDAO.saveHistoric(historic)
        }
    }
}