package com.wafflestudio.assignment4.views.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.wafflestudio.assignment4.R
import com.wafflestudio.assignment4.databinding.FragmentLoginBinding
import com.wafflestudio.assignment4.utils.eraseFromPreference
import com.wafflestudio.assignment4.utils.writeToPreference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.authenticate.setOnClickListener {
            context?.writeToPreference("token", binding.token.text.toString())
            CoroutineScope(Dispatchers.IO).launch {
                val (success, message) = loginViewModel.authenticate()
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    if (success) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        context?.getColor(R.color.red)?.let {
                            binding.authenticate.setBackgroundColor(it)
                        }
                        context?.eraseFromPreference("token")
                    }
                }
            }
        }
    }
}