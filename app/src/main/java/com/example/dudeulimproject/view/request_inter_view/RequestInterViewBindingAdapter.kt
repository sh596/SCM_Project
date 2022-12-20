package com.example.dudeulimproject.view.request_inter_view

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.R
import com.example.dudeulimproject.view.proceed_inter_view.adapter.RecordInterViewAdapter

object RequestInterViewBindingAdapter {
    @BindingAdapter("bindQuestionAdapter")
    @JvmStatic
    fun bindQuestionAdapter(view: RecyclerView, questionList: ObservableArrayList<String>?) {
        val adapter = view.adapter as RecordInterViewAdapter? ?: return
        if (questionList != null) {
            adapter.submitList(questionList.toMutableList())
        }
    }

    @BindingAdapter("bindCategoryToOnline")
    @JvmStatic
    fun bindCategoryToOnline(view: View, category: String) {
        if (category == "online") {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE

        }
    }

    @BindingAdapter("bindCategoryToOffline")
    @JvmStatic
    fun bindCategoryToOffline(view: View, category: String) {
        if (category == "online") {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE

        }
    }

    @BindingAdapter("bindIsRequester")
    @JvmStatic
    fun bindIsRequester(view: CardView, isRequester: Boolean) {
        if (isRequester) {
            view.setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.gray_100))
        } else {
            view.setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.blue_700))
        }
    }

    @BindingAdapter("bindIsRequester")
    @JvmStatic
    fun bindIsRequester(view: TextView, isRequester: Boolean) {
        if (isRequester) {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.gray_700))
            view.text = "수락 대기 중"
        } else {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.white))
            view.text = "요청 수락"
        }
    }

    @BindingAdapter("bindPlace")
    @JvmStatic
    fun bindPlace(view: TextView, place: String) {
        val placeList = place.split("|").toList()

        if (placeList.isNotEmpty()) {
            view.text = placeList[0]
        }
        if (placeList[0].isBlank()) {
            view.text = "미정"
        }
    }


    @BindingAdapter("bindDate")
    @JvmStatic
    fun bindDate(view: TextView, date: String) {
        if (date.isBlank()) {
            view.text = "미정"
        }else {
            view.text = date
        }
    }
}