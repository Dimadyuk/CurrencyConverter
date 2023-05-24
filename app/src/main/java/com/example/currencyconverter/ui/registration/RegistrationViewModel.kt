package com.example.currencyconverter.ui.registration

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.UserRepositoryImpl
import com.example.currencyconverter.domain.User
import com.example.currencyconverter.domain.usecases.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepositoryImpl(application)
    private val registerUseCase = RegisterUseCase(userRepository)

    fun registerUser(login: String, password: String, name: String, surname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newUser = User(login, password, name, surname)
            val result = registerUseCase.invoke(newUser)
            withContext(Dispatchers.Main) {
                if (result > 0) {
                    Toast.makeText(application, "$newUser added in database!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}