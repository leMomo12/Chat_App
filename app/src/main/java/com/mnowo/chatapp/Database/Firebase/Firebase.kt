package com.mnowo.chatapp.Database.Firebase

import android.util.Log
import android.util.Log.d
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mnowo.chatapp.Other.Class.User
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class Firebase @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()

     suspend fun registerWithEmailAndPassword(email: String, password: String) : Boolean {
        var isSuccessful = false
        try {
            val result = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()

            if(result.user != null) {
                isSuccessful = true
            }
        } catch (e: Exception) {
            d("register", "Exception while registration $e")
            isSuccessful = false
        }
        return isSuccessful
    }

     suspend fun loginWithEmailAndPassword(email: String, password: String) : Boolean {
         var isSuccessful = false
        try {
            val result = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()

            if(result.user != null) {
                isSuccessful = true
            }
        } catch (e: Exception) {
            isSuccessful = false
            d("login", "Exception logging in $e")
        }
        return isSuccessful
    }

    suspend fun addUserToFirestore(customId: String, user: User) {
        try {
            db.collection(customId).add(user).addOnSuccessListener {
                d("register", "Successfully added to firebase")
            }.addOnFailureListener {
                d("register", "Failed to add to firebase")
            }.await()

        } catch (e: Exception) {
            d("register", "Exception adding to firebase $e")
        }
    }
}