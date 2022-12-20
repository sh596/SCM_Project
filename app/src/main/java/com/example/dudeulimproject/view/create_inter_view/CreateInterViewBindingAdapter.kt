package com.example.dudeulimproject.view.create_inter_view

import android.net.Uri
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import org.w3c.dom.Text

object CreateInterViewBindingAdapter {

    @BindingAdapter("visibilityOfImageAddButton")
    @JvmStatic
    fun visibilityOfImageAddButton(view: View, uri: Uri?){
        if(uri!=null){
            view.visibility = View.INVISIBLE
        }else {
            view.visibility = View.VISIBLE
        }
    }
    @BindingAdapter("bindFragmentPosition")
    @JvmStatic
    fun bindFragmentPosition(view: TextView, position: Int){
        if(position == 1) {
            view.text = "생성"
        }else {
            view.text = "다음"
        }
    }

}