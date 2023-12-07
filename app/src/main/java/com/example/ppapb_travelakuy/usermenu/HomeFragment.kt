package com.example.ppapb_travelakuy.usermenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ppapb_travelakuy.databinding.UsermenuHomeFragmentBinding

class HomeFragment : AppCompatActivity() {
    private lateinit var binding: UsermenuHomeFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsermenuHomeFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}