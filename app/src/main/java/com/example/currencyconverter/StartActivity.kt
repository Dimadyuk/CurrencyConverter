package com.example.currencyconverter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.databinding.ActivityStartBinding
import com.example.currencyconverter.ui.SharedPreferencesKeys
import com.example.currencyconverter.ui.login.LoginFragment

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPrefs =
            getSharedPreferences(SharedPreferencesKeys.SHARED_PREF, Context.MODE_PRIVATE)
        val login = sharedPrefs.getString(SharedPreferencesKeys.LOGIN, null)
        val password = sharedPrefs.getString(SharedPreferencesKeys.PASSWORD, null)

        if (savedInstanceState == null) {
            val fragment = LoginFragment.newInstance(login, password)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }
}