package com.adielboanerge.interntest.third_screen.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retro {
    private val BASE_URL = "https://reqres.in/api/"

    fun getRetroClientInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val okHttpClient = OkHttpClient.Builder()
        .build()

}