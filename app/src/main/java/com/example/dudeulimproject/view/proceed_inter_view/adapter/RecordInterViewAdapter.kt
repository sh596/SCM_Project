package com.example.dudeulimproject.view.proceed_inter_view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.R
import com.example.dudeulimproject.databinding.ItemRecordBinding

class RecordInterViewAdapter(private val function: (Int, String, ImageView) -> Boolean) : ListAdapter<String, RecyclerView.ViewHolder>(
    DiffUtilCallback()
) {
    private lateinit var binding: ItemRecordBinding
    private var isPlay = false
    class DiffUtilCallback : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean = oldItem == newItem
    }

    inner class ViewHolder(private val binding: ItemRecordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String, position: Int) {
            val stringArr = item.split("/DDL")
            binding.record = stringArr[1]
            binding.playButton.setOnClickListener {
                function(position, item, binding.playButton)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_record, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(getItem(position), position)
    }

}