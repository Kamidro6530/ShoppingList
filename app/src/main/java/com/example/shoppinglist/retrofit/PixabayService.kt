package com.example.shoppinglist.retrofit

import com.example.shoppinglist.BuildConfig
import com.example.shoppinglist.pixabay.ImageList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {

    @GET("api/")
    fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Call<ImageList>


}