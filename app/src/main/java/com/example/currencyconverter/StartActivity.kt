package com.example.currencyconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.databinding.ActivityStartBinding
import com.example.currencyconverter.ui.login.LoginFragment

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = LoginFragment.newInstance()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }

    }
}