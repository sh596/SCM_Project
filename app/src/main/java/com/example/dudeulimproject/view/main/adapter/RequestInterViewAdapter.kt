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
import com.example.dudeulimproject.databinding.ItemRequestInterViewBinding
import com.example.dudeulimproject.view.request_inter_view.RequestInterViewActivity

class RequestInterViewAdapter : ListAdapter<RequestInterViewData, RecyclerView.ViewHolder>(
    DiffUtilCallback()
) {
    private lateinit var binding: ItemRequestInterViewBinding

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

    class ViewHolder(private val binding: ItemRequestInterViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: RequestInterViewData) {
            binding.interView = item
            if(item.isRequester){
                binding.imageRequestInterviewItemArrow.setImageResource(R.drawable.request_arrow_right)
            }else {
                binding.imageRequestInterviewItemArrow.setImageResource(R.drawable.request_arrow_left)
            }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, RequestInterViewActivity::class.java)
                intent.putExtra("requestInterViewId",item.id)
                intent.putExtra("isRequester", item.isRequester)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_request_inter_view, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(getItem(position))
    }

}