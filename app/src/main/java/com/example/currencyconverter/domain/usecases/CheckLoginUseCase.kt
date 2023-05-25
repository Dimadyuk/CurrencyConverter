package com.example.currencyconverter.domain.usecases

import com.example.currencyconverter.domain.UserRepository

class CheckLoginUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(login: String) =
        userRepository.checkLogin(login)
}