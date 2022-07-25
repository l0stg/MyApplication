package com.example.myapplicationapi.Data.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private const val BASE_URL = "https://api.punkapi.com/v2/"
    private var retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()).build()
    val api: RetrofitServices = retrofit.create(RetrofitServices::class.java)
}