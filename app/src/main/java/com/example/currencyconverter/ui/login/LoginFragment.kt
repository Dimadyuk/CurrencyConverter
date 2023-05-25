package com.example.currencyconverter.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.MainActivity
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.FragmentLoginBinding
import com.example.currencyconverter.ui.registration.RegistrationFragment

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[LoginViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setChangeListeners()
        observeViewModel()


    }

    private fun setClickListeners() {
        with(binding) {
            btnSignIn.setOnClickListener {
                viewModel.checkUser(etLogin.text.toString(), etPassword.text.toString())
            }
            tvSignUp.setOnClickListener {
                val fragment = RegistrationFragment.newInstance()
                parentFragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.container, fragment).commit()
            }

        }
    }

    private fun setChangeListeners() {
        with(binding) {
            val afterTextChangedListener = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    viewModel.loginDataChanged(
                        login = etLogin.text.toString(), password = etPassword.text.toString()
                    )
                }
            }
            etLogin.addTextChangedListener(afterTextChangedListener)
            etPassword.addTextChangedListener(afterTextChangedListener)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.loginFormState.observe(viewLifecycleOwner) { formState ->
            if (formState == null) {
                return@observe
            }
            with(binding) {
                btnSignIn.isEnabled = formState.isDataValid
                formState.loginError?.let { etLogin.error = getString(it) }
                formState.passwordError?.let { etPassword.error = getString(it) }
            }
        }

        viewModel.loginUserFormState.observe(viewLifecycleOwner) { formUserState ->
            with(binding) {
                formUserState.incorrectLogin?.let { etLogin.error = getString(it) }
                formUserState.incorrectPassword?.let { etPassword.error = getString(it) }
                if (formUserState.user != null){
                    val user = formUserState.user!!
                    val intent = MainActivity.newIntent(requireContext(), user)
                    startActivity(intent)
                }
            }
        }
    }

    companion object {

        fun newInstance() = LoginFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}