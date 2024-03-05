package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import com.google.firebase.auth.FirebaseAuth


class MesRecettes : BaseActivity(), OnDeleteClickListener {

    lateinit var recetteAdapter: AdapterMesrecettes
    private lateinit var listView: ListView

    override fun onDeleteClick(recette: Recettedb) {

        db.collection("recettes").document("cake")
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mes_recettes)
        recetteAdapter = AdapterMesrecettes(context = this, recettes = listOf(), this)
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

        val all=findViewById<TextView>(R.id.all)
        all.setOnClickListener {
            val all = Intent(this, Recette::class.java)
            startActivity(all) }

        val ajoutrect=findViewById<TextView>(R.id.ajout_recettes)
        ajoutrect.setOnClickListener {
            val ajoutderecettes = Intent(this, NewRecette::class.java)
            startActivity(ajoutderecettes) }

    }

    private fun hydrateAdapter(){
        val userconnected = getInitAuth().currentUser!!.uid

        getPseudo()

        db.collection(userconnected)
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

    private fun getPseudo(){
        val useridconnected = getInitAuth().currentUser!!.uid
        val titleTextView = findViewById<TextView>(R.id.pseudo)
        titleTextView.text = useridconnected

        val collection = "user"
        val documentname = useridconnected
        Log.d(TAG, "pseudo: $documentname")

        if (documentname != null) {
            db.collection(collection)
                .document(documentname)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val pseudouser = documentSnapshot.getString("pseudo")
                        val titleTextView = findViewById<TextView>(R.id.pseudo)
                        titleTextView.text = pseudouser+"'s recipies"
                    } else {
                        Log.d(TAG, "Pas de pseudo trouvé.")
                    }
                }
        }

    }
}

