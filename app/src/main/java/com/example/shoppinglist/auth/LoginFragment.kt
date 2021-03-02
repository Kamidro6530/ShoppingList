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
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val btnRegister = view.findViewById<Button>(R.id.login_registration_button)
        val btnLogin = view.findViewById<Button>(R.id.login_enter)

        btnLogin.setOnClickListener {
            mAuth.signInWithEmailAndPassword(
                login_emailET.text.toString(),
                login_passwordET.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    val action = LoginFragmentDirections.actionLoginFragmentToListFragment()
                    view.findNavController().navigate(action)
                    Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_listFragment)
                } else {
                    Toast.makeText(context, "Login Failed , try again !", Toast.LENGTH_LONG).show()
                }
            }

        }

        btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
            view.findNavController().navigate(action)
            Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registrationFragment)
        }
        return view
    }


}


