package com.example.dudeulimproject.view.inter_view.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dudeulimproject.UserData
import com.example.dudeulimproject.view.inter_view.model.InterViewData
import com.example.dudeulimproject.view.inter_view.model.Review

class InterViewViewModel : ViewModel() {
    private val _interViewData = MutableLiveData<InterViewData>()
    val interViewData: LiveData<InterViewData>
        get() = _interViewData

    init {
        _interViewData.value = InterViewData(
            0,
            "",
            UserData(0, "김병주", "", "학생", "", true),
            "목탁 디자이너",
            "목탁 디자이너",
            "온라인",
            "IT",
            "100",
            listOf(Review(1, UserData(1, "김형진", "", "개발자", "", false), 5, "오우 좆같아요"),
                Review(2, UserData(1, "김형진", "", "개발자", "", false), 5, "오우 좆같아요"),
                Review(3, UserData(1, "김형진", "", "개발자", "", false), 5, "오우 좆같아요"),)
        )
    }

    suspend fun requestInterView() {

    }
}