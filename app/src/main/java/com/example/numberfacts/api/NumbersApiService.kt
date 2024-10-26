package com.example.numberfacts.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {
    @GET("{number}")
    suspend fun getFact(
        @Path("number") number: String,
    ): Response<String>

    @GET("random/math")
    suspend fun getRandomFact(): Response<String>
}
