package com.example.dudeulimproject.view.create_inter_view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.dudeulimproject.NavigationFragment
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseFragment
import com.example.dudeulimproject.databinding.FragmentCreateInterView2Binding
import com.example.dudeulimproject.view.create_inter_view.viewmodel.CreateInterViewViewModel
import com.example.dudeulimproject.view.explore_inter_view.adapter.FieldSpinnerAdapter

class CreateInterViewFragment2 : BaseFragment<FragmentCreateInterView2Binding>(R.layout.fragment_create_inter_view_2) {
    private lateinit var fieldItems: Array<String>
    private val viewModel : CreateInterViewViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        fieldItems = resources.getStringArray(R.array.field_array)
        val fieldAdapter = FieldSpinnerAdapter(binding.root.context, fieldItems.toList())
        binding.cardCreateInterViewFragmentFieldSpinner.adapter = fieldAdapter
    }


}
