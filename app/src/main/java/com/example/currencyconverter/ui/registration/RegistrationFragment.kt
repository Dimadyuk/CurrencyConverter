package com.example.currencyconverter.ui.registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.MainActivity
import com.example.currencyconverter.databinding.FragmentRegistrationBinding


class RegistrationFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
    }

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            registerUser()
            startActivity(intent)
        }
    }

    private fun registerUser() {
        with(binding) {
            viewModel.registerUser(
                login = etLogin.text.toString(),
                password = etPassword.text.toString(),
                name = etName.text.toString(),
                surname = etSurname.text.toString()
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = RegistrationFragment().apply {
            arguments = Bundle().apply {}
        }
    }
}