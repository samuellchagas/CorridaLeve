package com.example.corridaleve.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Historic(
    val distancia: String,
    val tempo: String,
    val pace: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
