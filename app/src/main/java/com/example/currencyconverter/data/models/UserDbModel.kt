package com.example.currencyconverter.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDbModel(
    @PrimaryKey val login: String,
    val password: String,
    val name: String,
    val surname: String
)