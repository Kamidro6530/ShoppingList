package com.example.shoppinglist.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.database.ShopItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

//Repository oddziela źródła danych od reszty aplikacji
class FirebaseRepository {
    var auth = FirebaseAuth.getInstance()
    var cloud = FirebaseFirestore.getInstance()

    fun getList(): LiveData<ArrayList<ShopItem>> {
        val cloudResult =  MutableLiveData<ArrayList<ShopItem>>()
        val uid = auth.currentUser?.uid
        var list = ArrayList<ShopItem>()
        cloud.collection("lists")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d("TEST", "${document.id} => ${document.data}")
                        list.add(document.toObject(ShopItem::class.java))
                        cloudResult.postValue(list)
                    }
                }
            }

        return cloudResult
    }
}