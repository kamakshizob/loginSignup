package com.example.loginsignup.model.repository

import com.example.loginsignup.FirebaseAuthenticationService
import com.example.loginsignup.userEntity.User
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class UserRepositoryImpl(private val authService: FirebaseAuthenticationService) : UserRepository {
    override suspend fun login(email: String, password: String): FirebaseAuthenticationService.Result<User> {
        return suspendCancellableCoroutine { continuation ->
            authService.login(email, password) { result ->
                continuation.resume(result as FirebaseAuthenticationService.Result<User>)
            }
        }
    }

    override suspend fun signup(email: String, password: String): FirebaseAuthenticationService.Result<User> {
        return suspendCancellableCoroutine { continuation ->
            authService.signup(email, password) { result ->
                continuation.resume(result as FirebaseAuthenticationService.Result<User>)
            }
        }
    }
}
