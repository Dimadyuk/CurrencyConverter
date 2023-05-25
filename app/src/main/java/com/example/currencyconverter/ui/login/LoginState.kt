package com.example.currencyconverter.ui.login

data class LoginState(
    var loginError: Int? = null,
    var passwordError: Int? = null,

    var isDataValid: Boolean = false
)