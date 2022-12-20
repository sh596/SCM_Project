package com.example.dudeulimproject.view.create_inter_view.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.dudeulimproject.NavigationFragment
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseFragment
import com.example.dudeulimproject.databinding.FragmentCreateInterView1Binding
import com.example.dudeulimproject.view.create_inter_view.viewmodel.CreateInterViewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateInterViewFragment1 :
    BaseFragment<FragmentCreateInterView1Binding>(R.layout.fragment_create_inter_view_1){
    private val viewModel: CreateInterViewViewModel by activityViewModels()
    private val resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                if (uri != null) {
                    viewModel.setUri(uri)
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this
    }


    fun clickThumbnailImage(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        resultLauncher.launch(intent)
    }
}