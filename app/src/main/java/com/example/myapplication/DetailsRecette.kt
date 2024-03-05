package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.helper.widget.MotionEffect
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import com.google.firebase.firestore.FirebaseFirestore

class DetailsRecette : BaseActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_recette)

        val userconnected = getInitAuth().currentUser!!.uid

        val db = FirebaseFirestore.getInstance()

        val arr=findViewById<TextView>(R.id.retourarriere)
        arr.setOnClickListener {
            val retour = Intent(this,Recette::class.java)
            startActivity(retour)
        }



        val intent = intent
        if (intent.hasExtra("selectedTitle")) {
            val selectedTitle = intent.getStringExtra("selectedTitle")

            val titleTextView = findViewById<TextView>(R.id.titleDetails)
            titleTextView.text = selectedTitle

            val collection = "recettes"
            val documentname = selectedTitle
            Log.d(TAG, "Nom recette : $documentname")

            if (documentname != null) {
                db.collection(collection)
                    .document(documentname)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            val recipesend = documentSnapshot.getString("end")
                            val recipesingredients = documentSnapshot.get("ingredients") as? List<String>
                            val recipessteps = documentSnapshot.get("steps") as? List<String>
                            val recipesnumberOfPeople = documentSnapshot.getString("numberOfPeople")
                            val recipestimeToCook = documentSnapshot.getString("timeToCook")
                            val recipestimeToSpend = documentSnapshot.getString("timeToSpend")
                            val recipeseditor = documentSnapshot.getString("idediteur")
                            val recipesvalue = documentSnapshot.getString("allraitings")
                            val recipesraiting = documentSnapshot.getString("numbersofRaiting")
                            val recipesgrade = documentSnapshot.getString("grade")

                            val recetteslike = hashMapOf(
                                "title" to documentname,
                                "ingredients" to recipesingredients,
                                "steps" to recipessteps,
                                "end" to recipesend,
                                "idediteur" to recipeseditor,
                                "timeToCook" to recipestimeToCook,
                                "timeToSpend" to recipestimeToSpend,
                                "numberOfPeople" to recipesnumberOfPeople
                            )

                            var boutonClique = false
                            var value: String = ""
                            val firststar=findViewById<TextView>(R.id.firststar)
                            firststar.setOnClickListener {
                                if (!boutonClique ){
                                    boutonClique = true

                                    var raiting = "0"
                                    if (recipesraiting == null){
                                    } else { raiting = recipesraiting}

                                    val raitings = (raiting.toInt() + 1).toString()

                                    value = "1"

                                    var allraiting = "0"
                                    if (recipesvalue == null){
                                    } else { allraiting = recipesvalue}

                                    val allraitings = (allraiting.toInt() + value.toInt()).toString()

                                    var allgrade = "0"
                                    if (recipesgrade == null){
                                    } else { allgrade = recipesgrade}

                                    val allgrades = (allraitings.toDouble() / raitings.toDouble()).toString()

                                    Log.d(TAG, "value : $value")
                                    Toast.makeText(
                                        this,
                                        "Vous avez mis la note de : $value",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val titleTextView = findViewById<TextView>(R.id.valueedit)
                                    titleTextView.text = value + " étoiles"
                                    val valuestars = hashMapOf(
                                        "valuestars" to value,
                                        "grade" to allgrades,
                                        "allraitings" to allraitings,
                                        "numbersofRaiting" to raitings,
                                        "title" to documentname,
                                        "ingredients" to recipesingredients,
                                        "steps" to recipessteps,
                                        "end" to recipesend,
                                        "idediteur" to recipeseditor,
                                        "timeToCook" to recipestimeToCook,
                                        "timeToSpend" to recipestimeToSpend,
                                        "numberOfPeople" to recipesnumberOfPeople
                                    )

                                    val validervalue = findViewById<TextView>(R.id.validervalue)
                                    validervalue.setOnClickListener {
                                        db.collection("recettes").document(documentname)
                                            .set(valuestars)
                                            .addOnSuccessListener {
                                                Log.d(
                                                    MotionEffect.TAG,
                                                    "DocumentSnapshot successfully written!"
                                                )
                                            }
                                            .addOnFailureListener { e ->
                                                Log.w(MotionEffect.TAG, "Error adding document", e)
                                            }

                                    }
                                }}

                            val secondstar=findViewById<TextView>(R.id.secondstar)
                            secondstar.setOnClickListener {
                                if (!boutonClique ) {
                                    boutonClique = true

                                    var raiting = "0"
                                    if (recipesraiting == null){
                                    } else { raiting = recipesraiting}

                                    val raitings = (raiting.toInt() + 1).toString()

                                    value = "2"

                                    var allraiting = "0"
                                    if (recipesvalue == null){
                                    } else { allraiting = recipesvalue}

                                    val allraitings = (allraiting.toInt() + value.toInt()).toString()

                                    var allgrade = "0"
                                    if (recipesgrade == null){
                                    } else { allgrade = recipesgrade}

                                    val allgrades = (allraitings.toDouble() / raitings.toDouble()).toString()
                                    Log.d(TAG, "value : $value")
                                    Toast.makeText(
                                        this,
                                        "Vous avez mis la note de : $value",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val titleTextView = findViewById<TextView>(R.id.valueedit)
                                    titleTextView.text = value + " étoiles"
                                    val valuestars = hashMapOf(
                                        "valuestars" to value,
                                        "grade" to allgrades,
                                        "allraitings" to allraitings,
                                        "numbersofRaiting" to raitings,
                                        "title" to documentname,
                                        "ingredients" to recipesingredients,
                                        "steps" to recipessteps,
                                        "end" to recipesend,
                                        "idediteur" to recipeseditor,
                                        "timeToCook" to recipestimeToCook,
                                        "timeToSpend" to recipestimeToSpend,
                                        "numberOfPeople" to recipesnumberOfPeople
                                    )

                                    val validervalue = findViewById<TextView>(R.id.validervalue)
                                    validervalue.setOnClickListener {
                                        db.collection("recettes").document(documentname)
                                            .set(valuestars)
                                            .addOnSuccessListener {
                                                Log.d(
                                                    MotionEffect.TAG,
                                                    "DocumentSnapshot successfully written!"
                                                )
                                            }
                                            .addOnFailureListener { e ->
                                                Log.w(MotionEffect.TAG, "Error adding document", e)
                                            }
                                    }
                                }}

                            val thirdstar=findViewById<TextView>(R.id.thirdstar)
                            thirdstar.setOnClickListener {
                                if (!boutonClique ) {
                                    boutonClique = true

                                    var raiting = "0"
                                    if (recipesraiting == null){
                                    } else { raiting = recipesraiting}

                                    val raitings = (raiting.toInt() + 1).toString()

                                    value = "3"

                                    var allraiting = "0"
                                    if (recipesvalue == null){
                                    } else { allraiting = recipesvalue}

                                    val allraitings = (allraiting.toInt() + value.toInt()).toString()

                                    var allgrade = "0"
                                    if (recipesgrade == null){
                                    } else { allgrade = recipesgrade}

                                    val allgrades = (allraitings.toDouble() / raitings.toDouble()).toString()
                                    Log.d(TAG, "value : $value")
                                    Toast.makeText(
                                        this,
                                        "Vous avez mis la note de : $value",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val titleTextView = findViewById<TextView>(R.id.valueedit)
                                    titleTextView.text = value + " étoiles"
                                    val valuestars = hashMapOf(
                                        "valuestars" to value,
                                        "grade" to allgrades,
                                        "allraitings" to allraitings,
                                        "numbersofRaiting" to raitings,
                                        "title" to documentname,
                                        "ingredients" to recipesingredients,
                                        "steps" to recipessteps,
                                        "end" to recipesend,
                                        "idediteur" to recipeseditor,
                                        "timeToCook" to recipestimeToCook,
                                        "timeToSpend" to recipestimeToSpend,
                                        "numberOfPeople" to recipesnumberOfPeople
                                    )

                                    val validervalue = findViewById<TextView>(R.id.validervalue)
                                    validervalue.setOnClickListener {
                                        db.collection("recettes").document(documentname)
                                            .set(valuestars)
                                            .addOnSuccessListener {
                                                Log.d(
                                                    MotionEffect.TAG,
                                                    "DocumentSnapshot successfully written!"
                                                )
                                            }
                                            .addOnFailureListener { e ->
                                                Log.w(MotionEffect.TAG, "Error adding document", e)
                                            }
                                    }
                                }}

                            val fourstar=findViewById<TextView>(R.id.fourstar)
                            fourstar.setOnClickListener {
                                if (!boutonClique ) {
                                    boutonClique = true

                                    var raiting = "0"
                                    if (recipesraiting == null){
                                    } else { raiting = recipesraiting}

                                    val raitings = (raiting.toInt() + 1).toString()

                                    value = "4"

                                    var allraiting = "0"
                                    if (recipesvalue == null){
                                    } else { allraiting = recipesvalue}

                                    val allraitings = (allraiting.toInt() + value.toInt()).toString()

                                    var allgrade = "0"
                                    if (recipesgrade == null){
                                    } else { allgrade = recipesgrade}

                                    val allgrades = (allraitings.toDouble() / raitings.toDouble()).toString()
                                    Log.d(TAG, "value : $value")
                                    Toast.makeText(
                                        this,
                                        "Vous avez mis la note de : $value",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val titleTextView = findViewById<TextView>(R.id.valueedit)
                                    titleTextView.text = value + " étoiles"
                                    val valuestars = hashMapOf(
                                        "valuestars" to value,
                                        "grade" to allgrades,
                                        "allraitings" to allraitings,
                                        "numbersofRaiting" to raitings,
                                        "title" to documentname,
                                        "ingredients" to recipesingredients,
                                        "steps" to recipessteps,
                                        "end" to recipesend,
                                        "idediteur" to recipeseditor,
                                        "timeToCook" to recipestimeToCook,
                                        "timeToSpend" to recipestimeToSpend,
                                        "numberOfPeople" to recipesnumberOfPeople
                                    )

                                    val validervalue = findViewById<TextView>(R.id.validervalue)
                                    validervalue.setOnClickListener {
                                        db.collection("recettes").document(documentname)
                                            .set(valuestars)
                                            .addOnSuccessListener {
                                                Log.d(
                                                    MotionEffect.TAG,
                                                    "DocumentSnapshot successfully written!"
                                                )
                                            }
                                            .addOnFailureListener { e ->
                                                Log.w(MotionEffect.TAG, "Error adding document", e)
                                            }
                                    }
                                }}

                            val fivestar=findViewById<TextView>(R.id.fivestar)
                            fivestar.setOnClickListener {
                                if (!boutonClique ) {
                                    boutonClique = true

                                    var raiting = "0"
                                    if (recipesraiting == null){
                                    } else { raiting = recipesraiting}

                                    val raitings = (raiting.toInt() + 1).toString()

                                    value = "5"

                                    var allraiting = "0"
                                    if (recipesvalue == null){
                                    } else { allraiting = recipesvalue}

                                    val allraitings = (allraiting.toInt() + value.toInt()).toString()

                                    var allgrade = "0"
                                    if (recipesgrade == null){
                                    } else { allgrade = recipesgrade}

                                    val allgrades = (allraitings.toDouble() / raitings.toDouble()).toString()
                                    Log.d(TAG, "value : $value")
                                    Toast.makeText(
                                        this,
                                        "Vous avez mis la note de : $value",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val titleTextView = findViewById<TextView>(R.id.valueedit)
                                    titleTextView.text = value + " étoiles"
                                    val valuestars = hashMapOf(
                                        "valuestars" to value,
                                        "grade" to allgrades,
                                        "allraitings" to allraitings,
                                        "numbersofRaiting" to raitings,
                                        "title" to documentname,
                                        "ingredients" to recipesingredients,
                                        "steps" to recipessteps,
                                        "end" to recipesend,
                                        "idediteur" to recipeseditor,
                                        "timeToCook" to recipestimeToCook,
                                        "timeToSpend" to recipestimeToSpend,
                                        "numberOfPeople" to recipesnumberOfPeople
                                    )

                                    val validervalue = findViewById<TextView>(R.id.validervalue)
                                    validervalue.setOnClickListener {
                                        db.collection("recettes").document(documentname)
                                            .set(valuestars)
                                            .addOnSuccessListener {
                                                Log.d(
                                                    MotionEffect.TAG,
                                                    "DocumentSnapshot successfully written!"
                                                )
                                            }
                                            .addOnFailureListener { e ->
                                                Log.w(MotionEffect.TAG, "Error adding document", e)
                                            }
                                    }
                                }}


                            val ajoutlike=findViewById<TextView>(R.id.ajoutlike)
                            ajoutlike.setOnClickListener {
                                db.collection("listejaime"+userconnected).document(documentname)
                                    .set(recetteslike)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            this,
                                            "La recette a été ajouté à liste des j'aimes",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        Log.d(
                                            MotionEffect.TAG,
                                            "DocumentSnapshot successfully written!"
                                        )
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(MotionEffect.TAG, "Error adding document", e)
                                        Toast.makeText(
                                            this,
                                            "La recette n'a pas été ajouté à liste des j'aimes",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }}

                            val collection = "user"
                            val documentname1 = recipeseditor
                            Log.d(MotionEffect.TAG, "pseudo: $documentname1")

                            if (documentname1 != null) {
                                db.collection(collection)
                                    .document(documentname1)
                                    .get()
                                    .addOnSuccessListener { documentSnapshot ->
                                        if (documentSnapshot.exists()) {
                                            val pseudouser = documentSnapshot.getString("pseudo")
                                            val titleTextView = findViewById<TextView>(R.id.userpseudo)
                                            titleTextView.text = "Créée par :"+pseudouser

                                            val profilecreatoruser=findViewById<TextView>(R.id.userpseudo)
                                            profilecreatoruser.setOnClickListener {
                                            val intent =
                                                Intent(this, RecetteDeUserSelected::class.java)
                                            intent.putExtra("idediteur", recipeseditor)
                                            intent.putExtra("pseudouser", pseudouser)
                                            startActivity(intent) }

                                        } else {
                                            Log.d(MotionEffect.TAG, "Pas de pseudo trouvé.")
                                        }
                                    }
                            }

                            val endTextView = findViewById<TextView>(R.id.editEnd)
                            endTextView.text = recipesend

                            val numberOfPeopleTextView = findViewById<TextView>(R.id.editNumberOfPeople)
                            numberOfPeopleTextView.text = recipesnumberOfPeople

                            val timeToCookTextView = findViewById<TextView>(R.id.editTimeToCook)
                            timeToCookTextView.text = recipestimeToCook

                            val timeToSpendTextView = findViewById<TextView>(R.id.editTimeToSpend)
                            timeToSpendTextView.text = recipestimeToSpend

                            val ingrLView = findViewById<ListView>(R.id.listIngredients)
                            val ingredientsAdapter = ArrayAdapter<String>(
                                this,
                                android.R.layout.simple_list_item_1,
                                recipesingredients.orEmpty()
                            )
                            ingrLView.adapter = ingredientsAdapter

                            val etapesLView = findViewById<ListView>(R.id.listEtapes)
                            val etapesAdapter = ArrayAdapter<String>(
                                this,
                                android.R.layout.simple_list_item_1,
                                recipessteps.orEmpty()
                            )
                            etapesLView.adapter = etapesAdapter

//                            Log.d(TAG, "Commentaire : $recipesend")
//                            Log.d(TAG, "Ingrédients : $recipesingredients")
//                            Log.d(TAG, "Les étapes : $recipessteps")
                        } else {
                            Log.d(TAG, "Le document n'existe pas.")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Erreur lors de la récupération des données", exception)
                    }
            }
        }
    }

}

