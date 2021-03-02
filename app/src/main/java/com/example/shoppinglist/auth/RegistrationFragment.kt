package com.example.shoppinglist.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.shoppinglist.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment() {
    var mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mAuth.currentUser != null) {
            val action = LoginFragmentDirections.actionLoginFragmentToListFragment()
            view?.findNavController()?.navigate(action)
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_listFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        val btnRegister = view.findViewById<Button>(R.id.registration_enter)

        btnRegister.setOnClickListener {
            if (registration_passwordET.text.toString() == registration_passwordET2.text.toString()) {
                if (registration_passwordET.text.length >= 6 && registration_passwordET2.text.length >= 6) {
                    mAuth.createUserWithEmailAndPassword(
                        registration_emailET.text.toString(),
                        registration_passwordET.text.toString()
                    )
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val action =
                                    RegistrationFragmentDirections.actionRegistrationFragmentToListFragment()
                                view.findNavController().navigate(action)
                                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registrationFragment)
                            }

                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Registration failed", Toast.LENGTH_LONG).show()
                        }

                } else {
                    Toast.makeText(
                        context,
                        "Password would be longer than 5 chars",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(context, "Passwords're not the same", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }


}