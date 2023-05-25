package com.example.currencyconverter.domain

interface UserRepository {
    suspend fun insertUser(user: User): Long

    suspend fun getUser(login: String, password: String): User?
    suspend fun checkLogin(login: String): Boolean

}