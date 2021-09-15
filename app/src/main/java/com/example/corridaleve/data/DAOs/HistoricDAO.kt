package com.example.corridaleve.data.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.corridaleve.model.Historic

@Dao
interface HistoricDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHistoric(historic: Historic)

    @Query("SELECT * FROM Historic")
    fun returnHistorics(): List<Historic>

}