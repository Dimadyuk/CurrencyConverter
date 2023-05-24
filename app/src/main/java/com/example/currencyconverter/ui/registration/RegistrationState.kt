package com.example.currencyconverter.ui.registration

data class RegistrationState(
    var loginError: Int? = null,
    var passwordError: Int? = null,
    var nameError: Int? = null,
    var surnameError: Int? = null,

    var isDataValid: Boolean = false
)