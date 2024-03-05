package com.example.myapplication
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.constraintlayout.helper.widget.MotionEffect
import androidx.core.widget.NestedScrollView


class NewRecette : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ajout_recette)

        val scrollView = findViewById<NestedScrollView>(R.id.scrollView)
        val ingredientLV = findViewById<ListView>(R.id.ingredientsList)
        val stepsLV = findViewById<ListView>(R.id.stepsList)

        ingredientLV.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                scrollView.requestDisallowInterceptTouchEvent(true)
                val action = event.actionMasked
                when (action) {
                    MotionEvent.ACTION_UP -> scrollView.requestDisallowInterceptTouchEvent(
                        false
                    )
                }
                return false
            }
        })

        stepsLV.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                scrollView.requestDisallowInterceptTouchEvent(true)
                val action = event.actionMasked
                when (action) {
                    MotionEvent.ACTION_UP -> scrollView.requestDisallowInterceptTouchEvent(
                        false
                    )
                }
                return false
            }
        })

        val recipe = Recettedb()
        val newingredients = findViewById<Button>(R.id.button)
        newingredients.setOnClickListener{
            val ingredientsEditText = findViewById<EditText>(R.id.editIngredients)
            val newIngredient = ingredientsEditText.text.toString()
            recipe.ingredients.add(newIngredient)
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recipe.ingredients)
            ingredientLV.adapter = adapter
            ingredientsEditText.text.clear()
        }

        val newsteps = findViewById<Button>(R.id.button2)
        newsteps.setOnClickListener{
            val stepsEditText = findViewById<EditText>(R.id.editSteps)
            val newSteps = stepsEditText.text.toString()
            recipe.steps.add(newSteps)
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recipe.steps)
            stepsLV.adapter = adapter
            stepsEditText.text.clear()
        }


        val goback = findViewById<TextView>(R.id.goback)
        goback.setOnClickListener {
            val goback = Intent(this, Recette::class.java)
            startActivity(goback)
        }

        val confirmajoutrecettes = findViewById<TextView>(R.id.boutonajoutrecettes)
        confirmajoutrecettes.setOnClickListener {
            recipe.title = findViewById<EditText>(R.id.editNomRecette).text.toString()
            recipe.numberOfPeople = findViewById<EditText>(R.id.editNumberOfPeople).text.toString()
            recipe.timeToSpend = findViewById<EditText>(R.id.editTimeToSpend).text.toString()
            recipe.timeToCook = findViewById<EditText>(R.id.editTimeToCook).text.toString()
            recipe.end = findViewById<EditText>(R.id.editEnd).text.toString()
            recipe.idediteur = getInitAuth().currentUser!!.uid

//            val recettesingr = hashMapOf(
//                "nom_recette" to title,
//                "ingredients" to ingredients,
//                "steps" to steps,
//                "end" to end,
//                "idediteur" to idediteur
//            )

            db.collection("recettes").document(recipe.title)
                .set(recipe)
                .addOnSuccessListener {
                    Log.d(
                        MotionEffect.TAG,
                        "DocumentSnapshot successfully written!"
                    )
                }
                .addOnFailureListener { e ->
                    Log.w(MotionEffect.TAG, "Error adding document", e)
                }

            db.collection(recipe.idediteur).document(recipe.title)
                .set(recipe)
                .addOnSuccessListener {
                    Log.d(
                        MotionEffect.TAG,
                        "DocumentSnapshot successfully written!"
                    )
                }
                .addOnFailureListener { e ->
                    Log.w(MotionEffect.TAG, "Error adding document", e)
                }

            val goback = Intent(this, Recette::class.java)
            startActivity(goback)
        }
    }
}

