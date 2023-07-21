package com.example.loginsignup.userEntity

import com.google.firebase.auth.FirebaseUser

data class User(val id: String, val email: String) {
    constructor(user: FirebaseUser?) : this(user?.uid ?: "", user?.email ?: "")
}
