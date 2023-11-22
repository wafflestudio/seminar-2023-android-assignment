package com.wafflestudio.assignment4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.wafflestudio.assignment4.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginPref = viewModel.getLoginPref()
        Log.d("MA", "loginPref is $loginPref")
        if (!loginPref) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentView.id, LoginFragment())
                .commit()
        } else {
            replaceHomeFragment()
        }


    }
    private fun replaceHomeFragment() {
        Log.d("MA", "changed to HomeFragment")
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentView.id, homeFragment)
            .commit()
    }
}