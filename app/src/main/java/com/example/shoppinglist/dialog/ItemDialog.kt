package com.example.shoppinglist.dialog

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.shoppinglist.R
import com.example.shoppinglist.database.ShopItem
import com.example.shoppinglist.home.HomeActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlin.random.Random

class ItemDialog(context: Context?) {
    var database = FirebaseDatabase.getInstance()
    var firestore = FirebaseFirestore.getInstance()

    var db = Firebase.firestore
    fun showDialog(activity: Activity){
        activity.setContentView(R.layout.custom_alert_dialog)
        var enterButton = activity.findViewById<Button>(R.id.alert_enter)
        var imageView = activity.findViewById<ImageView>(R.id.alert_imageView)
        activity.alert_countNP.minValue = 0
        activity.alert_countNP.value = 0
        activity.alert_countNP.maxValue = 100


        enterButton.setOnClickListener {
            val name = activity.alert_nameET.text.toString()
            val count  = activity.alert_countNP.value.toFloat()
            val price  = activity.alert_priceET.text.toString().toFloat()
            val totalprice = price*count
            var randomNumber = Random.nextInt()
            val item = hashMapOf<String,String>()
            item["name"] = name
            item["path"] = name +"$count"+"$price"+"$randomNumber"
            item["count"] = count.toString()
            item["typeCount"] = "pln"
            item["price"] = price.toString()
            item["image"] = ""
            item["total_price"] = totalprice.toString()

            firestore.collection("lists")
                .document(name +"$count"+"$price"+"$randomNumber")
                .set(item)
                .addOnFailureListener { exception ->
                    Log.d("TAG","Add item to firestore error $exception")


                }
            val intent = Intent(activity,HomeActivity::class.java)
            startActivity(activity,intent,null)

        }

        imageView.setOnClickListener {
           Toast.makeText(activity.applicationContext, "IMAGE", Toast.LENGTH_SHORT).show()
        }

    }




}