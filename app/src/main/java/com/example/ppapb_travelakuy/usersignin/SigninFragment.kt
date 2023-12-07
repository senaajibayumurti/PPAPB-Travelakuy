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

class SigninFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = UsersigninSigninFragmentBinding.inflate(inflater, container, false)
        val button: Button = binding.buttonSignIn

        with(binding) {
            val mainActivity = requireActivity() as MainActivity
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
                } else if (
                    !checkboxTnc.isChecked
                ){
                    makeText(requireContext(),
                        "Please accept our Terms and Conditions.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val bundle = Bundle()
                    bundle.putString("username", username1)
                    bundle.putString("password", password1)

                    val adapter = mainActivity.viewPager2.adapter as FragmentStateAdapter
                    val loginFragment = adapter.createFragment(1) as LoginFragment
                    loginFragment.arguments = bundle
                    mainActivity.toLoginPage()

                    makeText(requireContext(),
                        "You Are Registered!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return binding.root
    }
}