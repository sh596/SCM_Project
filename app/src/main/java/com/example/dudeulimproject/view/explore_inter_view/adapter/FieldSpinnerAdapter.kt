package com.example.dudeulimproject.view.explore_inter_view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.dudeulimproject.R

class FieldSpinnerAdapter(private val context: Context, private val list: List<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long = 0

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_spinner_text,p2, false)
        val textView: TextView = view.findViewById(R.id.textSpinnerTextItem)
        textView.text = list[p0]
        return view
    }
}