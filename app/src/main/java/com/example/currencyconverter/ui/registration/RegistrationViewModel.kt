package com.example.currencyconverter.ui.registration

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.R
import com.example.currencyconverter.data.UserRepositoryImpl
import com.example.currencyconverter.domain.User
import com.example.currencyconverter.domain.usecases.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepositoryImpl(application)
    private val registerUseCase = RegisterUseCase(userRepository)

    private val _registerFormState = MutableLiveData<RegistrationState>()
    val registerFormState: LiveData<RegistrationState> = _registerFormState

    fun registerUser(login: String, password: String, name: String, surname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newUser = User(login, password, name, surname)
            val result = registerUseCase.invoke(newUser)
            withContext(Dispatchers.Main) {
                if (result > 0) {
                    Toast.makeText(
                        application,
                        "Thanks  for registration, ${newUser.name}!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun registerDataChanged(login: String, password: String, name: String, surname: String) {
        val state = RegistrationState()
        if (!isTextValid(login)) {
            state.loginError = R.string.invalid_login
        }
        if (!isTextValid(password)) {
            state.passwordError = R.string.invalid_password
        }
        if (!isTextValid(name)) {
            state.nameError = R.string.invalid_name
        }
        if (!isTextValid(surname)) {
            state.surnameError = R.string.invalid_surname
        }

        state.isDataValid = state.loginError == null &&
                state.passwordError == null &&
                state.nameError == null &&
                state.surnameError == null

        _registerFormState.value = state
    }

    private fun isTextValid(str: String): Boolean {
        return str.length > 3
    }
}