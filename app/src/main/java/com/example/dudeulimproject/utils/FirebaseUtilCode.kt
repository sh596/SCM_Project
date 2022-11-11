package com.example.dudeulimproject.utils

import com.example.dudeulimproject.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

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
