package com.example.dudeulimproject.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseDialog<T: ViewDataBinding>(context: Context, @LayoutRes private val layoutId: Int): Dialog(context) {
    lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
    }
}