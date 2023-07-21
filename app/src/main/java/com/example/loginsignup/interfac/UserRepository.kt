package com.example.loginsignup.model.repository

import com.example.loginsignup.FirebaseAuthenticationService
import com.example.loginsignup.userEntity.User

interface UserRepository {
    suspend fun login(
        email: String,
        password: String): FirebaseAuthenticationService.Result<User>
    suspend fun signup(email: String, password: String): FirebaseAuthenticationService.Result<User>
}
