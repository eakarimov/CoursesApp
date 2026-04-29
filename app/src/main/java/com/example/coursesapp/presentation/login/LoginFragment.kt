package com.example.coursesapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.coursesapp.R
import com.example.coursesapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

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

        collectUiState()
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
            viewModel.onEmailChanged(it.toString())
        }

        etPassword.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
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

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.btnLogin.isEnabled = uiState.isLoginEnabled
                }
            }
        }
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