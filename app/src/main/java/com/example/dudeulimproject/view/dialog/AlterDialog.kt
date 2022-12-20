package com.example.dudeulimproject.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.dudeulimproject.R
import com.example.dudeulimproject.databinding.DialogAlterBinding
import com.example.dudeulimproject.databinding.DialogNormalBinding

class AlterDialog(
    context: Context,
    private val title: String,
    private val content: String,
    private val function: () -> Unit
) : Dialog(context) {

    private lateinit var binding : DialogAlterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_normal, null, false)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setCancelable(false)    //다
        binding.textNormalDialogTitle.text = title
        binding.textNormalDialogContent.text = content
        binding.textNormalDialogPosButton.setOnClickListener {
            function()
            dismiss()
        }

    }
}