package com.example.shoppinglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.example.shoppinglist.auth.Login
import com.example.shoppinglist.auth.Registration
import com.example.shoppinglist.list.ListFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
}


    /*
    var isLogged = false
        var isRegistered = false
        setContentView(R.layout.activity_main)
        login.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        registration.setOnClickListener {
            val intent = Intent(this,Registration::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        var currentUser = auth.currentUser
        if (currentUser != null){
            val intent = Intent(this,ListActivity::class.java)
            startActivity(intent)
        }
    }
     */