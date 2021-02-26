package com.example.shoppinglist.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.pixabay.ImageList
import com.example.shoppinglist.repository.FirebaseRepository

class ListViewModel : ViewModel() {
    var  repository = FirebaseRepository()
    var images_list :MutableLiveData<ImageList>?



    init {
        images_list = repository.getListImages()
    }

        var list = repository.getList()

        fun searchImages(imageQuery: String) {
            repository.searchImages(imageQuery)

        }

         fun getListImages(): MutableLiveData<ImageList>? {

            return images_list

        }

    }