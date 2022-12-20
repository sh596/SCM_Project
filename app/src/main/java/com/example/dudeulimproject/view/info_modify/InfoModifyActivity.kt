package com.example.dudeulimproject.view.info_modify

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityInfoModifyBinding
import com.example.dudeulimproject.view.info_modify.viewModel.InfoModifyViewModel
import com.example.dudeulimproject.view.map.MapActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class InfoModifyActivity : BaseActivity<ActivityInfoModifyBinding>(R.layout.activity_info_modify) {
    private val viewModel: InfoModifyViewModel by viewModels()
    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                viewModel.setPlace(result.data?.getStringExtra("place").toString())
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initViewModel()
    }

    private fun initViewModel() {
        val isOnline = intent.getStringExtra("category") == "online"
        viewModel.initData(isOnline, intent.getStringExtra("place")!!,
            intent.getStringExtra("date")!!
        )
    }

    fun clickMapButton(view: View) {
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra("place", viewModel.place.value+"|"+viewModel.placeData.value)
        activityResultLauncher.launch(intent)
    }

    fun clickSaveButton(view: View) {
        viewModel.save()
        finish()
    }

}