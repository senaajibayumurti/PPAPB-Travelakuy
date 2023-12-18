package com.example.ppapb_travelakuy.usersignin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ppapb_travelakuy.databinding.UsersigninSigninFragmentBinding
import com.example.ppapb_travelakuy.listener.AuthListener
import com.example.ppapb_travelakuy.listener.FragmentListener

class SigninFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = UsersigninSigninFragmentBinding.inflate(inflater, container, false)
        val button: Button = binding.buttonSignIn

        with(binding) {
            val auth = requireActivity() as AuthListener
            val fragment = requireActivity() as FragmentListener

            button.setOnClickListener {
                val username1 = editTextUsername.text.toString()
                val email1 = editTextEmail.text.toString()
                val password1 = editTextPassword.text.toString()

                if (username1.isEmpty() ||
                    email1.isEmpty() ||
                    password1.isEmpty()
                ) {
                    makeText(
                        requireContext(),
                        "Please fill out the credentials.",
                        Toast.LENGTH_SHORT
                    ).show()
                }  else {
                    auth.RegisterAccount(username1, email1, password1)
                    fragment.toPage(1)
                }
            }
        }
        return binding.root
    }
}