package com.example.currencyconverter.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val login: String,
    val password: String,
    val name: String,
    val surname: String
) : Parcelable