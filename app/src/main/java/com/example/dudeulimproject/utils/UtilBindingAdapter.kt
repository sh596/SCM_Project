package com.example.dudeulimproject.utils

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentManager.TAG
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.storage.FirebaseStorage

object UtilBindingAdapter {
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage (view : ImageView, url: String?){
        if(url == null) {
            return
        }
        val storage = FirebaseStorage.getInstance()
        val storageReference =storage.reference
        val pathReference = storageReference.child("$url.jpg")

        pathReference.downloadUrl.addOnSuccessListener {
            Glide.with(view.context)
                .load(it)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(view)
        }.addOnFailureListener {
            Log.d(TAG, "loadImage: ${it.message}")
        }
    }
    @BindingAdapter("bindUri")
    @JvmStatic
    fun bindUri (view : ImageView, uri: Uri?){
        if(uri == null) {
            return
        }
        view.setImageURI(uri)
    }
    @BindingAdapter("bindBooleanInVisibility")
    @JvmStatic
    fun bindBooleanInVisibility(view: View, boolean: Boolean){
        if(boolean){
            view.visibility = View.VISIBLE
        }else {
            view.visibility = View.GONE
        }
    }

}
