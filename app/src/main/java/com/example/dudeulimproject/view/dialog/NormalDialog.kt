package com.example.dudeulimproject.view.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.dudeulimproject.R
import com.example.dudeulimproject.databinding.DialogNormalBinding

class NormalDialog(context: Context, private val title : String, private val content: String, private val function: () -> Unit) : Dialog(context) {

    private lateinit var binding : DialogNormalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_normal, null, false)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(binding.root)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        //다이얼로그에 사용할 xml 파일을 불러옴
        setCancelable(false)    //다
        binding.textNormalDialogTitle.text = title
        binding.textNormalDialogContent.text = content
        binding.textNormalDialogNegButton.setOnClickListener {
            dismiss()
        }
        binding.textNormalDialogPosButton.setOnClickListener {
            function()
            dismiss()
        }
    }
}