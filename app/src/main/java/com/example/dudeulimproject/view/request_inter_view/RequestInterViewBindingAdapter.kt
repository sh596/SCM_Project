package com.example.dudeulimproject.view.request_inter_view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.view.request_inter_view.adapter.QuestionAdapter

object RequestInterViewBindingAdapter {
    @BindingAdapter("bindQuestionAdapter")
    @JvmStatic
    fun bindQuestionAdapter(view: RecyclerView, questionList: List<String>?) {
        val adapter = view.adapter as QuestionAdapter? ?: return
        if (questionList != null) {
            adapter.submitList(questionList)
        }
    }
}