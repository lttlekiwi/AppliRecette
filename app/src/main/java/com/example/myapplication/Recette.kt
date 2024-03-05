package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth


class Recette : BaseActivity() {

    lateinit var recetteAdapter: Adapter
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recette)
        recetteAdapter = Adapter(this, listOf())
        listView = findViewById(R.id.listview_recettes)
        listView.adapter = recetteAdapter

        hydrateAdapter()

        val deco=findViewById<TextView>(R.id.deconnexion)
        deco.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val deconnexion = Intent(this, MainActivity::class.java)
            startActivity(deconnexion) }

        val like=findViewById<TextView>(R.id.like)
        like.setOnClickListener {
            val like = Intent(this, LikedRecipies::class.java)
            startActivity(like) }

        val home=findViewById<TextView>(R.id.home)
        home.setOnClickListener {
            val home = Intent(this, MesRecettes::class.java)
            startActivity(home) }

        val ajoutrect=findViewById<TextView>(R.id.ajout_recettes)
        ajoutrect.setOnClickListener {
            val ajoutderecettes = Intent(this, NewRecette::class.java)
            startActivity(ajoutderecettes) }

    }

    private fun hydrateAdapter(){
        db.collection("recettes")
            .get()
            .addOnSuccessListener { result ->
                val recettesList = mutableListOf<Recettedb>()

                for (document in result) {
                    val recette = document.toObject(Recettedb::class.java)
                    recettesList.add(recette)
                }
                recetteAdapter.updateList(recettesList)

            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }
}

