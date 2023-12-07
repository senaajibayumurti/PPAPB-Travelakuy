package com.example.ppapb_travelakuy.usersignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.ppapb_travelakuy.databinding.UsersigninActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var viewPager2:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "MyTabLayout"

        var binding = UsersigninActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            viewPager.adapter = TabAdapter(this@MainActivity)
            viewPager2 = viewPager

            TabLayoutMediator(tabLayout, viewPager){
                    tab, position ->
                tab.text = when(position){
                    0 -> "Register"
                    1 -> "Log In"
                    else -> ""
                }
            }.attach()
        }
    }
    fun toLoginPage(){
        viewPager2.setCurrentItem(1, true)
    }
}