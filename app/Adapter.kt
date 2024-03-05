package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myapplication.R

class Adapter (private val context: Context, private val recettes: Array<String>) : BaseAdapter(){

    override fun getCount(): Int {
        return  recettes.size
    }

    companion object {
        private var inflater: LayoutInflater? = null
    }

    init {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    class Holder{
        lateinit var title: TextView
    }
    private fun initHolder(view: View): Holder {
        val holder = Holder()
        holder.title = view.findViewById(R.id.title)
        holder.title.maxLines = 1
        holder.title.isSelected = true
        holder.title.isSingleLine = true
        holder.title.isFocusable = true
        holder.title.isFocusableInTouchMode = true

        return holder
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cv = convertView

        if (cv == null) {
            cv = inflater!!.inflate(R.layout.recettes_layout, parent, false)
        }
        val holder = initHolder(cv!!)
        holder.title.text= recettes[position].title

        return cv
    }



}
