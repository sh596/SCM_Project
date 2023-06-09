package com.example.dudeulimproject.view.explore_inter_view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.R
import com.example.dudeulimproject.data.Comment
import com.example.dudeulimproject.databinding.ItemReviewBinding
import com.example.dudeulimproject.data.Review

class ReviewAdapter : ListAdapter<Comment, RecyclerView.ViewHolder>(DiffUtilCallback()) {
    private lateinit var binding: ItemReviewBinding

    class DiffUtilCallback : DiffUtil.ItemCallback<Comment>(){
        override fun areItemsTheSame(
            oldItem: Comment,
            newItem: Comment
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Comment,
            newItem: Comment
        ): Boolean = oldItem.id == newItem.id
    }

    class ViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Comment) {
            binding.review = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_review, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(getItem(position))
    }

}