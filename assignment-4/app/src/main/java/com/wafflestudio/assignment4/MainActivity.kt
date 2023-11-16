package com.wafflestudio.assignment4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.wafflestudio.assignment4.databinding.ActivityMainBinding
import com.wafflestudio.assignment4.databinding.FragmentHomeBinding
import com.wafflestudio.assignment4.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel:LoginViewModel by viewModels()
    @Inject
    lateinit var api:RestAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}