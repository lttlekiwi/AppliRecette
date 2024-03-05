package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.FirebaseApp

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        val currentUser = getInitAuth().currentUser
        if (currentUser != null) {
            val inv = Intent(this, Recette::class.java)
            startActivity(inv)
        }

        val ConnexionButton=findViewById<Button>(R.id.boutonco)

        ConnexionButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.case_email)
            val str_email = email.text.toString()
            val mdp = findViewById<EditText>(R.id.case_mdp)
            val str_mdp = mdp.text.toString()

            loginUser(str_email, str_mdp)
        }

        val inscrip=findViewById<TextView>(R.id.inscription)

        inscrip.setOnClickListener {
            val ins = Intent(this, Inscription::class.java)
            startActivity(ins)
        }

        val invit=findViewById<TextView>(R.id.invite)

        invit.setOnClickListener {
            val inv = Intent(this, Recette::class.java)
            startActivity(inv)
        }
    }
}
