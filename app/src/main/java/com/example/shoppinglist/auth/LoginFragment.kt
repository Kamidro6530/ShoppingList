package com.example.shoppinglist.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.shoppinglist.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    var mAuth = FirebaseAuth .getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)
      //  val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as? NavHostFragment
       // val navController = navHostFragment?.navController
        var btnRegister = view?.findViewById<Button>(R.id.login_registration_button) as? Button
       btnRegister?.setOnClickListener {
           Toast.makeText(view.context,"dsa",Toast.LENGTH_SHORT).show()
        //   navController?.navigate(R.id.action_loginFragment_to_registrationFragment)


        }

        return view
    }


}