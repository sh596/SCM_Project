package com.example.dudeulimproject.view.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.view.explore_inter_view.ExploreInterViewActivity
import com.example.dudeulimproject.R
import com.example.dudeulimproject.databinding.ItemExploreInterviewBinding
import com.example.dudeulimproject.data.ExploreInterViewData

class ExploreInterViewAdapter : ListAdapter<ExploreInterViewData, RecyclerView.ViewHolder>(DiffUtilCallback()) {
    private lateinit var binding: ItemExploreInterviewBinding

    class DiffUtilCallback : DiffUtil.ItemCallback<ExploreInterViewData>(){
        override fun areItemsTheSame(
            oldItem: ExploreInterViewData,
            newItem: ExploreInterViewData
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ExploreInterViewData,
            newItem: ExploreInterViewData
        ): Boolean = oldItem.id == newItem.id
    }

    class ViewHolder(private val binding: ItemExploreInterviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ExploreInterViewData) {
            binding.interView = item
            binding.root.setOnClickListener {
                binding.root.context.startActivity(Intent(binding.root.context, ExploreInterViewActivity::class.java))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_explore_interview, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(getItem(position))
    }

}