package com.example.ppapb_travelakuy.usermenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ppapb_travelakuy.databinding.UsermenuProfileFragmentBinding

class ProfileFragment : AppCompatActivity() {
    private lateinit var binding: UsermenuProfileFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsermenuProfileFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}