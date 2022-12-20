package com.example.dudeulimproject.utils

import android.content.Context
import android.widget.Toast
import com.example.dudeulimproject.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Singleton

object FirebaseUtilCode {
    fun getFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
    private var storageInstance: FirebaseStorage? = null
    fun getStorage(): FirebaseStorage {
        if (storageInstance == null) {
            storageInstance = FirebaseStorage.getInstance()
        }
        return storageInstance as FirebaseStorage
    }

}
