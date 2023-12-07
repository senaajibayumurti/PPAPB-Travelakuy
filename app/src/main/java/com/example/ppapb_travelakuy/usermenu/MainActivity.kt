package com.example.ppapb_travelakuy.usermenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ppapb_travelakuy.databinding.UsermenuActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: UsermenuActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsermenuActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}