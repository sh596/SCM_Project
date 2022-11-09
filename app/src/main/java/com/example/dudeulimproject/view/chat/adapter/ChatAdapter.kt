package com.example.dudeulimproject.view.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.R
import com.example.dudeulimproject.data.ChatData
import com.example.dudeulimproject.databinding.ItemReviewBinding
import com.example.dudeulimproject.data.Review
import com.example.dudeulimproject.databinding.ItemChatBinding
import com.example.dudeulimproject.databinding.ItemMyChatBinding

class ChatAdapter(private val user: String) : ListAdapter<ChatData, RecyclerView.ViewHolder>(DiffUtilCallback()) {

    class DiffUtilCallback : DiffUtil.ItemCallback<ChatData>(){
        override fun areItemsTheSame(
            oldItem: ChatData,
            newItem: ChatData
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ChatData,
            newItem: ChatData
        ): Boolean = oldItem == newItem
    }

    class MyChatViewHolder(private val binding: ItemMyChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ChatData) {
            binding.chat = item
        }
    }

    class NormalChatViewHolder(private val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ChatData) {
            binding.chat = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> {
                val binding = ItemMyChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MyChatViewHolder(binding)
            }
            1 -> {
                val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                NormalChatViewHolder(binding)
            }
            else -> {
                val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                NormalChatViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MyChatViewHolder -> holder.onBind(getItem(position))
            is NormalChatViewHolder -> holder.onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).user == user) 0 else 1
    }

}