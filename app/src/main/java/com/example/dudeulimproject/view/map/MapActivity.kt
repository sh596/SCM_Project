package com.example.dudeulimproject.view.map

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityMapBinding
import com.example.dudeulimproject.view.map.viewModel.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.libraries.places.api.model.Place
import dagger.hilt.android.AndroidEntryPoint

class MapActivity : BaseActivity<ActivityMapBinding>(R.layout.activity_map), OnMapReadyCallback {

    private val viewModel: MapViewModel by viewModels()
    private val previousMarker = null
    private lateinit var mMap: GoogleMap
    private var placeName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initPlace()
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.layoutMapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initPlace() {
        val place = intent.getStringExtra("place")
        if (!place.isNullOrBlank()) {
            val placeArr = place.split("|").toList()
            placeName = placeArr[0]
            if (placeArr.size == 3) {
                viewModel.setMarker(LatLng(placeArr[1].toDouble(), placeArr[2].toDouble()))
            }
        }
    }

    fun clickBackButton(view: View){
        finish()
    }

    fun clickMarkingButton(view: View) {
        mMap.clear()
        viewModel.setMarker(viewModel.pointerMarker.value!!)
        viewModel.marker.value?.let {
            val markerPosition = LatLng(it.latitude, it.longitude)
            val markerOptions = MarkerOptions()
            markerOptions.position(markerPosition)
            markerOptions.icon(vectorToBitmap(R.drawable.map_marker))
            mMap.addMarker(markerOptions)
            val intent = Intent()

            viewModel.marker.value?.let{
                intent.putExtra("place", "${placeName}|${it.latitude}|${it.longitude}")
            }
            setResult(RESULT_OK,intent)

        }
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(37.494870, 126.960763)));
        viewModel.marker.value?.let {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(it));
        }
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14F));

        viewModel.pointerMarker.value?.let {
            val markerPosition = LatLng(it.latitude, it.longitude)
            val markerOptions = MarkerOptions()
            markerOptions.position(markerPosition)
            markerOptions.icon(vectorToBitmap(R.drawable.circle_marker))
            mMap.addMarker(markerOptions)
        }
        viewModel.marker.value?.let {
            val markerPosition = LatLng(it.latitude, it.longitude)
            val markerOptions = MarkerOptions()
            markerOptions.position(markerPosition)
            markerOptions.icon(vectorToBitmap(R.drawable.map_marker))
            mMap.addMarker(markerOptions)
        }
        mMap.setOnMapClickListener {
            mMap.clear()
            viewModel.marker.value?.let { value ->
                val markerPosition = LatLng(value.latitude, value.longitude)
                val markerOptions = MarkerOptions()
                markerOptions.position(markerPosition)
                markerOptions.icon(vectorToBitmap(R.drawable.map_marker))
                mMap.addMarker(markerOptions)
            }
            val pointerMarkerPosition = LatLng(it.latitude, it.longitude)
            val pointerMarkerOptions = MarkerOptions()
            pointerMarkerOptions.position(pointerMarkerPosition)
            pointerMarkerOptions.icon(vectorToBitmap(R.drawable.circle_marker))
            viewModel.setPointer(pointerMarkerPosition)
            mMap.addMarker(pointerMarkerOptions)

        }
    }

    private fun vectorToBitmap(@DrawableRes id: Int): BitmapDescriptor? {
        val vectorDrawable = ResourcesCompat.getDrawable(resources, id, null)!!
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


}