package com.example.currencyconverter.domain.usecases

import com.example.currencyconverter.domain.User
import com.example.currencyconverter.domain.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) = userRepository.insertUser(user)

}