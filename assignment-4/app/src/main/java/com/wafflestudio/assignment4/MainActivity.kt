package com.wafflestudio.assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wafflestudio.assignment4.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.loginFragmentView.id, LoginFragment()) // YourFragment()는 추가할 Fragment입니다.
                .commit()
        }
    }
}