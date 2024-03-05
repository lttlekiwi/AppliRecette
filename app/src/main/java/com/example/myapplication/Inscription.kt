package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class Inscription : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)

        val goback=findViewById<TextView>(R.id.goback)

        goback.setOnClickListener {
            val goback = Intent(this, MainActivity::class.java)
            startActivity(goback)
        }

        val boutonvalider=findViewById<TextView>(R.id.boutonvalider)

        boutonvalider.setOnClickListener {
            val mdp = findViewById<EditText>(R.id.case_mdp)
            val verifmdp = findViewById<EditText>(R.id.case_verifmdp)
            val str_mdp = mdp.text.toString()
            val str_verifmdp = verifmdp.text.toString()
            val email = findViewById<EditText>(R.id.case_email)
            val str_email = email.text.toString()
            val testemail ="@"


            if(str_mdp != str_verifmdp) {
                Toast.makeText(
                    this,
                    "Les mots de passe ne sont pas identiques, réessayez",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if(str_email.contains(testemail) ) {
                    if ( str_mdp.length >= 6 ) {
                        registerUser(str_email, str_mdp)
                        //val boutonvaliderr = Intent(this, Pseudo::class.java)
                        //startActivity(boutonvaliderr)
                    }
                    else {
                        Toast.makeText(
                            this,
                            "Le mot de passe doit être supérieur ou égal à 6 caractères",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else {
                    Toast.makeText(
                        this,
                        "Rentrez une adresse email valide",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }

}