package com.wafflestudio.assignment4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.wafflestudio.assignment4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //can I find Fragment by binding.fragment??
        val mainFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val controller = mainFragment.navController


        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val token = sharedPref.getString("token","")

        if(token != "") controller.navigate(R.id.action_loginFragment_to_homeFragment)

    }
}