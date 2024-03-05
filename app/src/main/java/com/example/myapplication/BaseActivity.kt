package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

open class BaseActivity: AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    open val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
    }

    fun getInitAuth(): FirebaseAuth {
        if(!::auth.isInitialized){
            auth = FirebaseAuth.getInstance()
        }
        return auth
    }

    fun registerUser(email: String, password: String) {
        getInitAuth()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val boutonvaliderr = Intent(this, Pseudo::class.java)
                    startActivity(boutonvaliderr)
                } else {
                    // Registration failed
                    Log.w("Registration", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this,
                        "Something went wrong. Try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
                Log.w("Registration", it.message.toString())
            }
    }

    fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val conn = Intent(this, Recette::class.java)
                    startActivity(conn)
                    val user = auth.currentUser
                } else {
                    // Login failed
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                    Log.w("Registration", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this,
                        "Something went wrong. Try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}