package com.example.loginsignup

import com.example.loginsignup.userEntity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthenticationService {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    sealed class Result<out T : Any> {
        data class Success<out T : Any>(val data: T) : Result<T>()
        data class Error(val exception: Exception) : Result<Nothing>()
    }

    fun login(email: String, password: String, onResult: (Result<User>) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    if (user != null) {
                        onResult(Result.Success(User(user)))
                    } else {
                        onResult(Result.Error(Exception("User not found")))
                    }
                } else {
                    onResult(Result.Error(task.exception ?: Exception("Unknown error")))
                }
            }
    }

    fun signup(email: String, password: String, onResult: (Result<User>) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    if (user != null) {
                        onResult(Result.Success(User(user)))
                    } else {
                        onResult(Result.Error(Exception("User not found")))
                    }
                } else {
                    onResult(Result.Error(task.exception ?: Exception("Unknown error")))
                }
            }
    }
}
