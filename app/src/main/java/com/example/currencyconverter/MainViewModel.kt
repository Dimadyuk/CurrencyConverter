package com.example.currencyconverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.domain.User

class MainViewModel() : ViewModel() {
    private val _user = MutableLiveData<User>()
    var user: LiveData<User> = _user

    fun setUser(user: User) {
        _user.value = user
    }
}