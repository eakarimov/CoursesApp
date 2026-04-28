package com.example.coursesapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.coursesapp.R
import com.example.coursesapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val emailRegex = Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupView() = with(binding) {
        btnLogin.isEnabled = false

        etEmail.filters = arrayOf(
            InputFilter { input, _, _, _, _, _ ->
                if (input.any { it.code > 127 }) "" else null
            })
    }

    private fun setupListeners() = with(binding) {
        etEmail.doAfterTextChanged {
            updateLoginButtonState()
        }

        etPassword.doAfterTextChanged {
            updateLoginButtonState()
        }

        btnLogin.setOnClickListener {
            navigateToMain()
        }

        btnVk.setOnClickListener {
            openInBrowser("https://vk.com/")
        }

        btnOk.setOnClickListener {
            openInBrowser("https://ok.ru/")
        }
    }

    private fun openInBrowser(url: String) = startActivity(
        Intent(
            Intent.ACTION_VIEW, url.toUri()
        )
    )

    private fun updateLoginButtonState() {
        val emailIsCorrect = binding.etEmail.text.toString().matches(emailRegex)
        val passwordIsCorrect = binding.etPassword.text.isNotBlank()

        binding.btnLogin.isEnabled = emailIsCorrect && passwordIsCorrect
    }

    private fun navigateToMain() {
        findNavController().navigate(
            R.id.main_graph, null, navOptions {
                popUpTo(R.id.auth_graph) {
                    inclusive = true
                }
            })
    }
}