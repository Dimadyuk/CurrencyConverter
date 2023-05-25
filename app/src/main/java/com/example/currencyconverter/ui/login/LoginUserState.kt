package com.example.currencyconverter.ui.login

import com.example.currencyconverter.domain.User

data class LoginUserState(
    var incorrectLogin: Int? = null,
    var incorrectPassword: Int? = null,
    var user: User? = null
)