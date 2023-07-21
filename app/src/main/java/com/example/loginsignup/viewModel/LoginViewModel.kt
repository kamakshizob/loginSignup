package com.example.loginsignup.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsignup.model.repository.UserRepository
import com.example.loginsignup.FirebaseAuthenticationService
import com.example.loginsignup.userEntity.User
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<FirebaseAuthenticationService.Result<User>>()
    val loginResult: LiveData<FirebaseAuthenticationService.Result<User>> = _loginResult
    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError
    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = userRepository.login(email, password)
        }
    }
    fun password(password: String) {
        if (password.length < 6) {
            _passwordError.value = "Password should be at least 6 characters long."
        } else {
            _passwordError.value = null
        }
    }
    fun email(email: String) {
        if (email.isEmpty()) {
            _emailError.value = "Email cannot be empty."
        } else if (!isValidEmail(email)) {
            _emailError.value = "Invalid email format."
        } else {
            _emailError.value = null //for clear any previous msg
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
        return email.matches(emailRegex)
    }

}

class SignupViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _signupResult = MutableLiveData<FirebaseAuthenticationService.Result<User>>()
    val signupResult: LiveData<FirebaseAuthenticationService.Result<User>> = _signupResult
    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError
    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            _signupResult.value = userRepository.signup(email, password)
        }
    }
    fun password(password: String) {
        if (password.length < 6) {
            _passwordError.value = "Password should be at least 6 characters long."
        } else {
            _passwordError.value = null
        }
    }

    fun email(email: String) {
        if (email.isEmpty()) {
            _emailError.value = "Email cannot be empty."
        } else if (!isValidEmail(email)) {
            _emailError.value = "Invalid email format."
        } else {
            _emailError.value = null //for clear any previous msg
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
        return email.matches(emailRegex)
    }
}
