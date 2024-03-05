package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

interface OnDeleteClickListener {
    fun onDeleteClick(recette: Recettedb)
}

class AdapterMesrecettes (private val context: Context, private var recettes: List<Recettedb>, private val onDeleteClickListener: OnDeleteClickListener) : BaseAdapter() {

    private var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    fun updateList(list: MutableList<Recettedb>) {
        this.recettes = list.toList()
        notifyDataSetChanged()
    }

    class Holder {
        lateinit var title: TextView
        lateinit var supprimer: TextView
    }

    private fun initHolder(view: View): Holder {
        val holder = Holder()
        holder.title = view.findViewById(R.id.title)
        holder.supprimer = view.findViewById(R.id.supprimer)
        holder.title.maxLines = 1
        holder.title.isSelected = true
        holder.title.isSingleLine = true
        holder.title.isFocusable = true
        holder.title.isFocusableInTouchMode = true
        return holder
    }

    override fun getCount(): Int {
        return recettes.size
    }

    override fun getItem(position: Int): Any {
        return recettes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cv = convertView
        if (cv == null) {
            cv = inflater.inflate(R.layout.liste_layoutmesrecettes, parent, false)
        }
        val holder = initHolder(cv!!)

        val currentRecette = recettes[position]
        holder.title.text = currentRecette.title

        holder.title.setOnClickListener {
            val detailsrecette = Intent(context, DetailsRecette::class.java)
            detailsrecette.putExtra("selectedTitle", currentRecette.title)
            context.startActivity(detailsrecette)
        }

        holder.supprimer.setOnClickListener {
            onDeleteClickListener.onDeleteClick(currentRecette)
        }

        return cv
    }
}
