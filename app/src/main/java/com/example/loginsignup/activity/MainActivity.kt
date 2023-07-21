package com.example.loginsignup.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.loginsignup.R
import com.example.loginsignup.databinding.ActivityMainBinding
import com.example.loginsignup.model.repository.UserRepository
import com.example.loginsignup.view.viewmodel.LoginViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val userRepository = UserRepository()

        val viewModel = LoginViewModel(userRepository)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        // handling error here
        viewModel.emailError.observe(this) { error ->
            val emailErrorTextView: TextView = findViewById(R.id.tv_email_error)
            if (error != null) {
                emailErrorTextView.text = error
            } else {
                emailErrorTextView.text = ""
            }
        }
        viewModel.passwordError.observe(this) { error ->
            val passwordErrorTextView: TextView = findViewById(R.id.tv_password_error)
            if (error != null) {
                passwordErrorTextView.text = error
            } else {
                passwordErrorTextView.text = ""
            }
        }


    }

}
