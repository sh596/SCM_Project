package com.example.dudeulimproject.view.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.R
import com.example.dudeulimproject.data.RequestInterViewData
import com.example.dudeulimproject.databinding.ItemProceedInterViewBinding
import com.example.dudeulimproject.view.proceed_inter_view.ProceedInterViewActivity
import com.example.dudeulimproject.view.request_inter_view.RequestInterViewActivity

class ProceedInterViewAdapter : ListAdapter<RequestInterViewData, RecyclerView.ViewHolder>(
    DiffUtilCallback()
) {
    private lateinit var binding: ItemProceedInterViewBinding

    class DiffUtilCallback : DiffUtil.ItemCallback<RequestInterViewData>(){
        override fun areItemsTheSame(
            oldItem: RequestInterViewData,
            newItem: RequestInterViewData
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RequestInterViewData,
            newItem: RequestInterViewData
        ): Boolean = oldItem.id == newItem.id
    }

    class ViewHolder(private val binding: ItemProceedInterViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: RequestInterViewData) {
            binding.interView = item
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, ProceedInterViewActivity::class.java)
                intent.putExtra("proceedInterViewId",item.id)
                intent.putExtra("isRequester", item.isRequester)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_proceed_inter_view, parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(getItem(position))
    }

}