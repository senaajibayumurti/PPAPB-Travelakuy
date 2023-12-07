package com.example.ppapb_travelakuy.usersignin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ppapb_travelakuy.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LoginFragmentBinding.inflate(inflater, container, false)
        val bundle = arguments
        val username2 = bundle?.getString("username")
        val password2 = bundle?.getString("password")
        val un = "Username"
        val pw = "Password"

        with(binding){
            btnLogIn.setOnClickListener {
                if (editTextUsernameLogIn.text.toString() != username2){
                    credentialIncorrectToast(un)
                } else if (editTextPasswordLogIn.text.toString() != password2){
                    credentialIncorrectToast(pw)
                }
            }
        }
        return binding.root
    }
    fun credentialIncorrectToast(credential:String){
        Toast.makeText(
            requireContext(),
            "${credential} tidak terdaftar",
            Toast.LENGTH_SHORT
        ).show()
    }
}