package com.example.corridaleve.repository

import com.example.corridaleve.data.DAOs.HistoricDAO
import com.example.corridaleve.model.Historic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoricRepository(private val historicDAO: HistoricDAO) {

    suspend fun returnListHistoric(): List<Historic>{

        return withContext(Dispatchers.IO){
            historicDAO.returnHistorics()
        }

    }

}