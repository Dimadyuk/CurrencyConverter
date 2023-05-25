package com.example.currencyconverter.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.R
import com.example.currencyconverter.data.UserRepositoryImpl
import com.example.currencyconverter.domain.usecases.CheckLoginUseCase
import com.example.currencyconverter.domain.usecases.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val application: Application) : AndroidViewModel(application) {
    private val userRepository = UserRepositoryImpl(application)
    private val getUseCase = GetUserUseCase(userRepository)
    private val checkLoginUseCase = CheckLoginUseCase(userRepository)

    private val _loginFormState = MutableLiveData<LoginState>()
    val loginFormState: LiveData<LoginState> = _loginFormState

    private val _loginUserFormState = MutableLiveData<LoginUserState>()
    val loginUserFormState: LiveData<LoginUserState> = _loginUserFormState

    fun loginUser(login: String, password: String) {

        viewModelScope.launch {

            val loginState = LoginUserState()
            if (!isLoginValid(login)) {
                loginState.incorrectLogin = R.string.no_such_user
            }
            withContext(Dispatchers.IO) {
                loginState.user = getUseCase(login, password)
            }


            if (loginState.user == null) {
                loginState.incorrectPassword = R.string.incorrect_password
            }
            _loginUserFormState.value = loginState
        }
    }


    private suspend fun isLoginValid(login: String): Boolean {
        return checkLoginUseCase(login)
    }

    fun loginDataChanged(login: String, password: String) {
        val state = LoginState()
        if (!isTextValid(login)) {
            state.loginError = R.string.invalid_login
        }
        if (!isTextValid(password)) {
            state.passwordError = R.string.invalid_password
        }

        state.isDataValid = state.loginError == null &&
                state.passwordError == null

        _loginFormState.value = state
    }

    private fun isTextValid(str: String): Boolean {
        return str.length > 3
    }
}