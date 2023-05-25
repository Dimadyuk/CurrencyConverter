package com.example.currencyconverter.domain.usecases

import com.example.currencyconverter.domain.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(login: String, password: String) =
        userRepository.getUser(login, password)
}