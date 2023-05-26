package com.example.currencyconverter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.example.currencyconverter.domain.User
import com.example.currencyconverter.ui.DialogLogOutFragment
import com.example.currencyconverter.ui.SharedPreferencesKeys
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), DialogLogOutFragment.DialogListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseExtras()
        setNavController()
        setOnClickListeners()
        observeViewModel()
    }

    private fun parseExtras() {
        val user = intent?.getParcelableExtra(USER) as? User
        if (user != null) {
            viewModel.setUser(user)
        }
    }

    private fun setNavController() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    private fun setOnClickListeners() {
        val dialogFragment =
            supportFragmentManager.findFragmentByTag("dialog") as? DialogLogOutFragment
                ?: DialogLogOutFragment().apply {
                    setDialogListener(this@MainActivity)
                }

        binding.btnLogout.setOnClickListener {
            dialogFragment.show(supportFragmentManager, "dialog")
        }
    }

    private fun observeViewModel() {
        viewModel.user.observe(this) {
            binding.tvUserName.text = "${it?.name} ${it?.surname}"
        }
    }

    override fun onStop() {
        super.onStop()

        val login = viewModel.user.value?.login
        val password = viewModel.user.value?.password
        val sharedPreferences = getSharedPreferences(
            SharedPreferencesKeys.SHARED_PREF, Context.MODE_PRIVATE
        )
        sharedPreferences.edit().apply {
            putString(SharedPreferencesKeys.LOGIN, login)
            putString(SharedPreferencesKeys.PASSWORD, password)
            apply()
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    private fun cleanUser() {
        viewModel.setUser(null)
    }

    companion object {
        private const val USER = "extra_user"
        fun newIntent(context: Context, user: User): Intent {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra(USER, user)
            }
            return intent
        }
    }

    override fun onDialogPositiveClick() {
        finish()
        cleanUser()
    }
}