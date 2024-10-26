package com.example.numberfacts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.numberfacts.api.ApiClient
import com.example.numberfacts.database.AppDatabase
import com.example.numberfacts.database.NumberFact
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
    private val apiService = ApiClient.service
    private val database =
        Room
            .databaseBuilder(
                application,
                AppDatabase::class.java,
                "numbers-db",
            ).build()

    val facts: LiveData<List<NumberFact>> = database.numberFactDao().getAllFacts()

    private val _factById = MutableLiveData<NumberFact?>()
    val factById: LiveData<NumberFact?> = _factById

    fun getFact(
        number: String,
        isRandom: Boolean,
    ) {
        viewModelScope.launch {
            val response =
                if (isRandom) {
                    apiService.getRandomFact()
                } else {
                    apiService.getFact(number)
                }

            if (response.isSuccessful) {
                response.body()?.let { fact ->
                    val numberText = if (isRandom) "Random" else number
                    val numberFact = NumberFact(number = numberText, fact = fact)
                    database.numberFactDao().insertFact(numberFact)
                }
            }
        }
    }

    fun loadFactById(factId: Int) {
        viewModelScope.launch {
            _factById.value = database.numberFactDao().getFactById(factId)
        }
    }
}
