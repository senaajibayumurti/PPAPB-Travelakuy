package com.example.ppapb_travelakuy.usermenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ppapb_travelakuy.R
import com.example.ppapb_travelakuy.databinding.UsermenuActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: UsermenuActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UsermenuActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        binding.botnavUsermen.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.orderDetailFragment) {
                binding.botnavUsermen.visibility = View.GONE
            } else {
                binding.botnavUsermen.visibility = View.VISIBLE
            }
        }
    }
}