package com.example.shoppinglist.retrofit

import android.media.Image
import retrofit2.Call
import retrofit2.http.GET

interface PixabayService {
    @GET("/")
    fun getImageByName(image : String) : Call<List<Image>>
}