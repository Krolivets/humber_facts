package com.example.numberfacts.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberFact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val number: String,
    val fact: String,
)
