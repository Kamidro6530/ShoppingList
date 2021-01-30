package com.example.shoppinglist.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.R
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (mAuth.currentUser != null) {
          //  startActivity(Intent(this, ListActivity::class.java))
        } else {
            setContentView(R.layout.activity_login)
            super.onCreate(savedInstanceState)

            login_enter.setOnClickListener {
               var email = login_emailET.text.toString()
               var password = login_passwordET.text.toString()

                mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        task ->
                        if (!task.isSuccessful){
                            Toast.makeText(applicationContext,"Nie poprawna nazwa użytkownika lub hasło",Toast.LENGTH_SHORT).show()
                        }else{
                          //  startActivity(Intent(this, ListActivity::class.java))
                        }
                    }
            }
        }


    }
}