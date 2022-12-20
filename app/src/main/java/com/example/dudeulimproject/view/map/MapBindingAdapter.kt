package com.example.dudeulimproject.view.map

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.dudeulimproject.R
import com.google.android.gms.maps.model.LatLng

object MapBindingAdapter {

    @BindingAdapter("bindPointerMarker")
    @JvmStatic
    fun bindPointerMarker(view : CardView, latLng: LatLng?) {
        if (latLng == null) {
            view.setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.gray_100))
        } else {
            view.setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.blue_700))
        }
    }


    @BindingAdapter("bindPointerMarker")
    @JvmStatic
    fun bindPointerMarker(view : TextView, latLng: LatLng?) {
        if (latLng == null) {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.gray_300))
        } else {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.white))
        }
    }

}