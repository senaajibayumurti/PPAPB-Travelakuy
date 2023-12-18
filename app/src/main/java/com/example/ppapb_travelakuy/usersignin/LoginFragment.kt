package com.example.ppapb_travelakuy.usersignin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ppapb_travelakuy.databinding.UsersigninLoginFragmentBinding
import com.example.ppapb_travelakuy.listener.AuthListener

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = UsersigninLoginFragmentBinding.inflate(inflater, container, false)
        val auth = requireActivity() as AuthListener

        with(binding){
            btnLogIn.setOnClickListener {
                auth.setAccount(editTextUsernameLogIn.text.toString(), editTextPasswordLogIn.text.toString())
            }
        }
        return binding.root
    }

}