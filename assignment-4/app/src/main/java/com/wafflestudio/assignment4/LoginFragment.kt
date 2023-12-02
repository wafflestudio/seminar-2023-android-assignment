package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.wafflestudio.assignment4.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val LOGIN_PARAM1 = "loginParam1"
private const val LOGIN_PARAM2 = "loginParam2"

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var loginBinding: FragmentLoginBinding
    private var loginParam1: String? = null
    private var loginParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            loginParam1 = it.getString(LOGIN_PARAM1)
            loginParam2 = it.getString(LOGIN_PARAM2)
        }
        checkTokenPresence()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginButton()
    }

    private fun checkTokenPresence() {
        if (!MyApplication.prefs.getString("token", "").isEmpty()) {
            navigateToHome()
        }
    }

    private fun setupLoginButton() {
        loginBinding.loginButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                if (loginViewModel.login(loginBinding.loginButton.text.toString())) {
                    navigateToHome()
                }
            }
        }
    }

    private fun navigateToHome() {
        try {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            MyApplication.prefs.setString("token", loginBinding.loginButton.text.toString())
        } catch (e: Exception) {
            Log.d("LoginFragment", e.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(loginParam1: String, loginParam2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(LOGIN_PARAM1, loginParam1)
                    putString(LOGIN_PARAM2, loginParam2)
                }
            }
    }
}
