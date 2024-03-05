package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.helper.widget.MotionEffect

class Pseudo : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_pseudo)

        val gotomeal=findViewById<TextView>(R.id.boutonvalider)

        gotomeal.setOnClickListener {
            val pseudo = findViewById<EditText>(R.id.editPseudo)
            val strpseudo = hashMapOf<String, String>(
                "pseudo" to pseudo.text.toString()
            )

            db.collection("user").document(getInitAuth().currentUser!!.uid)
                .set(strpseudo)
                .addOnSuccessListener {
                    Log.d(
                        MotionEffect.TAG,
                        "DocumentSnapshot successfully written!"
                    )
                }
                .addOnFailureListener { e ->
                    Log.w(MotionEffect.TAG, "Error adding document", e)
                }

            val valider = Intent(this, Recette::class.java)
            startActivity(valider)

        }

    }

}

