package com.example.dudeulimproject.view.map.viewModel

import android.location.Address
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MapViewModel : ViewModel() {
    private val _marker = MutableLiveData<LatLng>()
    val marker : LiveData<LatLng>
        get() = _marker
    private val _pointerMarker = MutableLiveData<LatLng>()
    val pointerMarker : LiveData<LatLng>
        get() = _pointerMarker

    fun setMarker(latLng: LatLng){
        _marker.value = latLng
    }

    fun setPointer(latLng: LatLng) {
        _pointerMarker.value = latLng
    }
}