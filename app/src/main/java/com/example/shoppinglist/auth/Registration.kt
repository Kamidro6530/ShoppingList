package com.example.shoppinglist.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {
    var mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_registration)
        super.onCreate(savedInstanceState)

        registration_enter.setOnClickListener {
            val firstpassword = registration_passwordET.text.toString()
            val secondpassword = registration_passwordET2.text.toString()
            val email = registration_emailET.text.toString()
            if (firstpassword == "" || secondpassword == "" || email == "") {
                Toast.makeText(
                    applicationContext,
                    "Żadne pole nie może być puste !",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (registration_passwordET.length() < 8) {
                    Toast.makeText(
                        applicationContext,
                        "Hasło jest zbyt krótkie  !",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (firstpassword == secondpassword) {


                    mAuth.createUserWithEmailAndPassword(email, secondpassword)
                        .addOnCompleteListener { task ->
                            Log.d("TAG", "new user registration ${task.isSuccessful}")

                            if (!task.isSuccessful) {
                                Log.d("TAG", "error registration ${task.exception}")
                                Toast.makeText(
                                    applicationContext,
                                    "Ten użytkownik już insteje/Nie prawdiłowy email",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val intent = Intent(this, Login::class.java)
                                startActivity(intent)

                            }
                        }

                } else {
                    Toast.makeText(
                        applicationContext,
                        "Hasła nie są takie same !",
                        Toast.LENGTH_LONG
                    ).show()
                    registration_passwordTV.text = firstpassword
                    registration_passwordTV2.text = secondpassword
                }
            }
        }
    }
}