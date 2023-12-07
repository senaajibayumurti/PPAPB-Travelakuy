package com.example.ppapb_travelakuy.usermenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ppapb_travelakuy.databinding.UsermenuHistoryFragmentBinding

class HistoryFragment : AppCompatActivity() {
    private lateinit var binding: UsermenuHistoryFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsermenuHistoryFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}