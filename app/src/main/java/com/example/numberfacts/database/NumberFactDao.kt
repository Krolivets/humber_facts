package com.example.numberfacts.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NumberFactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFact(fact: NumberFact)

    @Query("SELECT * FROM NumberFact ORDER BY id DESC")
    fun getAllFacts(): LiveData<List<NumberFact>>

    @Query("SELECT * FROM NumberFact WHERE id = :factId LIMIT 1")
    suspend fun getFactById(factId: Int): NumberFact?
}
