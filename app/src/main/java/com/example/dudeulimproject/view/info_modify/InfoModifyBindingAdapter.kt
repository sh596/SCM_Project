package com.example.dudeulimproject.view.info_modify

import android.icu.util.ULocale.Category
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter

object InfoModifyBindingAdapter {
    @BindingAdapter("bindCategoryOfPlace")
    @JvmStatic
    fun bindCategoryOfPlace(view: LinearLayout, category: Boolean){
        if(category){
            view.visibility = View.GONE
        }else {
            view.visibility = View.VISIBLE
        }
    }
}