package com.mnowo.chatapp.Database

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception
import javax.inject.Inject

class Firebase @Inject constructor() {

     fun registerWithEmailAndPassword(email: String, password: String) : Boolean {
        var isSuccessful = false
        try {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                isSuccessful = if (it.isSuccessful) {
                    Log.d("register", "Successfully registered")
                    true

                } else {
                    Log.d("register", "Failed to register")
                    Log.d("register", "email: $email, password: $password, ${it.isSuccessful}")
                    false
                }
            }
        } catch (e: Exception) {
            Log.d("register", "Exception while registration $e")
            isSuccessful = false
        }
        return isSuccessful
    }

    suspend fun loginWithEmailAndPassword(email: String, password: String) : Boolean {
        var isSuccessful = false
        try {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                isSuccessful = if(it.isSuccessful) {
                    Log.d("login", "Successfully logged in")
                    true
                } else {
                    Log.d("login", "Failed logging in")
                    false
                }
            }
        } catch (e: Exception) {
            isSuccessful = false
            Log.d("login", "Exception logging in $e")
        }
        return isSuccessful
    }
}