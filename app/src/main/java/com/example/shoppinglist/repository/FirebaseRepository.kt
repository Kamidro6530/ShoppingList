package com.example.shoppinglist.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.database.ShopItem
import com.example.shoppinglist.pixabay.ImageList
import com.example.shoppinglist.pixabay.ImageResponse
import com.example.shoppinglist.retrofit.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



//Repository oddziela źródła danych od reszty aplikacji
class FirebaseRepository  {
    var auth = FirebaseAuth.getInstance()
    var cloud = FirebaseFirestore.getInstance()
    var images_list = MutableLiveData<ImageList>()

    //Pobieranie bazy danych Cloud Firestore
    fun getList(): LiveData<ArrayList<ShopItem>> {
        val cloudResult =  MutableLiveData<ArrayList<ShopItem>>()

        var list = ArrayList<ShopItem>()
        cloud.collection("lists")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        list.add(document.toObject(ShopItem::class.java))
                        cloudResult.postValue(list)
                    }
                }
            }

        return cloudResult
    }

    //Zwracanie listy pobranych obrazów
    fun getListImages() : MutableLiveData<ImageList>? {

        return images_list
    }

     fun searchImages(imageQuery : String){

        RetrofitClient.instance.searchForImage(imageQuery).enqueue(object : Callback<ImageList>{
            override fun onFailure(call: Call<ImageList>, t: Throwable) {
                images_list.postValue(null)
                Log.d("TAG","Repository ERROR")
            }

            override fun onResponse(call: Call<ImageList>, response: Response<ImageList>) {
                if (response.body() != null){
                    Log.d("TAG","Repository WORK")
                    images_list.postValue(response.body())
                }
            }

        })
    }




}