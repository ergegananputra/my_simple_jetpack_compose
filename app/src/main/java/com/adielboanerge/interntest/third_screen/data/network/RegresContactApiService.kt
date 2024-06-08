package com.adielboanerge.interntest.third_screen.data.network

import com.adielboanerge.interntest.third_screen.data.model.RegresContactResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



interface RegresContactApiService {

//    users?page=1&per_page=10
    @GET("users")
    fun getContacts(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Call<RegresContactResponse>
}