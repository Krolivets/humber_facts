package com.example.numberfacts.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberFact::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun numberFactDao(): NumberFactDao
}
