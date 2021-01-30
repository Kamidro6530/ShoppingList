package com.example.shoppinglist.mvvm

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.repository.FirebaseRepository

class ListViewModel : ViewModel(){
    private val repository = FirebaseRepository()

    var list = repository.getList()
}