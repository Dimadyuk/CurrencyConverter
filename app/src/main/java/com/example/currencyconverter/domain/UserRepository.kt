package com.example.currencyconverter.domain

interface UserRepository {
    suspend fun insertUser(user: User): Long

}