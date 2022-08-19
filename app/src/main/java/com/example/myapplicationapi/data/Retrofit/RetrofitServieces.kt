package com.example.myapplicationapi.data.Retrofit

import com.example.data.models.DataBaseModel
import retrofit2.Response
import retrofit2.http.*

interface RetrofitServices {
    // EndPoint
    @GET("beers")

    // Получение ответа
    suspend fun getItemList(
        @Query("page") page: Int
    ): Response<List<DataBaseModel>>
}