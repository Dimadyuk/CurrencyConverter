package com.example.currencyconverter.data

import android.app.Application
import com.example.currencyconverter.domain.User
import com.example.currencyconverter.domain.UserRepository

class UserRepositoryImpl(application: Application) : UserRepository {
    private val userDao = AppDataBase.getInstance(application).userDao()
    private val mapper = UserMapper()

    override suspend fun insertUser(user: User) = userDao.insertUser(mapper.mapUserToDb(user))
    override suspend fun getUser(login: String, password: String): User? {
        val userDb = userDao.getUser(login, password)
        return userDb?.let { mapper.mapDbToUser(userDb) }
    }
    override suspend fun checkLogin(login: String): Boolean {
        val user = userDao.checkLogin(login)
        return user != null
    }
}
