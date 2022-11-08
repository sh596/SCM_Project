package com.example.dudeulimproject.view.explore_inter_view

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.R
import com.example.dudeulimproject.view.explore_inter_view.adapter.ReviewAdapter
import com.example.dudeulimproject.data.Review

object ExploreInterViewBindingAdapter {

    @BindingAdapter("setReview")
    @JvmStatic
    fun setReview(view: RecyclerView, list: List<Review>?) {
        val adapter = view.adapter as ReviewAdapter? ?: return
        if (list != null) {
            adapter.submitList(list.toMutableList())
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @BindingAdapter(value = ["number", "star"], requireAll = false)
    @JvmStatic
    fun setStar(view: ImageView, number: Int, star: Int) {
        if (number < star) {
            view.setColorFilter(view.context.getColor(R.color.yellow_700))
        } else {
            view.setColorFilter(view.context.getColor(R.color.yellow_700))
        }
    }


    @BindingAdapter("recyclerVisibility")
    @JvmStatic
    fun setStar(view: TextView, list : List<Review>?) {
        if(list != null){
            if(list.isNotEmpty()) {
                view.visibility = View.GONE
            }else {
                view.visibility = View.VISIBLE
            }
        }
    }

}
