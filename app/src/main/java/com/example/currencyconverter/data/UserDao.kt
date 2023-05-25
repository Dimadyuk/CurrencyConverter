package com.example.currencyconverter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverter.data.models.UserDbModel
import retrofit2.http.GET

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDbModel): Long

    @Query("SELECT * FROM user_info WHERE login = :login AND password= :password LIMIT 1")
    suspend fun getUser(login: String, password: String): UserDbModel?

    @Query("SELECT * FROM user_info WHERE login = :login LIMIT 1")
    suspend fun checkLogin(login: String): UserDbModel?
}