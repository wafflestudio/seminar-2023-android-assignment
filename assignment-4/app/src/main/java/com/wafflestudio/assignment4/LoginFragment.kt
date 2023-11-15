package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wafflestudio.assignment4.databinding.LoginFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginFragment: Fragment(R.layout.login_fragment) {
    private lateinit var binding: LoginFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginFragmentBinding.inflate(layoutInflater)

        binding.loginButton.setOnClickListener{
            Log.d("aaaa","Aaaa")
            var key = binding.enterApiKey.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                try{
                    viewModel.tryLogin(key)
                }
                catch (e: HttpException){
                    Toast.makeText(context, e.response()?.errorBody()?.string(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}