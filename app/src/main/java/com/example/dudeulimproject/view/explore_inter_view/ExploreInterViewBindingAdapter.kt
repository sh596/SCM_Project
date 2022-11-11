package com.example.dudeulimproject.view.explore_inter_view

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.R
import com.example.dudeulimproject.data.Comment
import com.example.dudeulimproject.view.explore_inter_view.adapter.ReviewAdapter
import com.example.dudeulimproject.data.Review

object ExploreInterViewBindingAdapter {

    @BindingAdapter("setReview")
    @JvmStatic
    fun setReview(view: RecyclerView, list: List<Comment>?) {
        val adapter = view.adapter as ReviewAdapter? ?: return
        if (list != null) {
            adapter.submitList(list.toMutableList())
        }
    }


    @BindingAdapter("bindCategory")
    @JvmStatic
    fun bindCategory(view: TextView, category: String?) {
        when (category) {
            "online" -> {
                view.text = "온라인 인터뷰"
            }
            "offline" -> {
                view.text = "오프라인 인터뷰"
            }
            else -> {
                view.text = ""
            }
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
    fun setStar(view: TextView, list : List<Comment>?) {
        if(list != null){
            if(list.isNotEmpty()) {
                view.visibility = View.GONE
            }else {
                view.visibility = View.VISIBLE
            }
        }
    }

}
