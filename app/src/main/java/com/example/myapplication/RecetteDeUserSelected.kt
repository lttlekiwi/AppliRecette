package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.helper.widget.MotionEffect


class RecetteDeUserSelected : BaseActivity() {


    lateinit var recetteAdapter: Adapter
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recette_user_selected)
        recetteAdapter = Adapter(this, listOf())
        listView = findViewById(R.id.listview_recettes)
        listView.adapter = recetteAdapter

        val intent = intent
        val idEditeur = intent.getStringExtra("idediteur")
        val pseudoUser = intent.getStringExtra("pseudouser")

        hydrateAdapter()

        val titleTextView = findViewById<TextView>(R.id.pseudo)
        titleTextView.text = pseudoUser+"'s recipies"
        Log.d(MotionEffect.TAG, "pseudo="+pseudoUser)

        val retourarr=findViewById<TextView>(R.id.retourarriere)
        retourarr.setOnClickListener {
            val retourarr = Intent(this, Recette::class.java)
            startActivity(retourarr) }

        }

    private fun hydrateAdapter(){
        val userconnected = getInitAuth().currentUser!!.uid
        val intent = intent
        val idEditeur = intent.getStringExtra("idediteur")
        val pseudoUser = intent.getStringExtra("pseudouser")

        if (idEditeur != null) {
            db.collection(idEditeur)
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
                } } } }






