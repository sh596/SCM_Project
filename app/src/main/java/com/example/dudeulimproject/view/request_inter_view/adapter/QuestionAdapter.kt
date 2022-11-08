package com.example.dudeulimproject.view.request_inter_view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.R
import com.example.dudeulimproject.databinding.ItemReviewBinding
import com.example.dudeulimproject.data.Review
import com.example.dudeulimproject.databinding.ItemQuestionBinding

class QuestionAdapter : ListAdapter<String, RecyclerView.ViewHolder>(DiffUtilCallback()) {
    private lateinit var binding: ItemQuestionBinding

    class DiffUtilCallback : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean = oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    class ViewHolder(private val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {
            binding.question = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_question, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(getItem(position))
    }

}