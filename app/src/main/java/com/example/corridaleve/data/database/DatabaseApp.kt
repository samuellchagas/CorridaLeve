package com.example.corridaleve.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.corridaleve.data.DAOs.HistoricDAO
import com.example.corridaleve.model.Historic

@Database(
    entities = [Historic::class],
    version = 1,
    exportSchema = false
)

abstract class DatabaseApp : RoomDatabase() {

    abstract fun historicDAO(): HistoricDAO

    companion object {

        private const val NAME_DB = "Database App"

        fun getInstance(context: Context?): DatabaseApp {
            return Room
                .databaseBuilder(context!!, DatabaseApp::class.java, NAME_DB)
                .build()
        }

    }

}
