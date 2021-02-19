package com.example.worldvisit.ws

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.eu/rest/v2/all")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}