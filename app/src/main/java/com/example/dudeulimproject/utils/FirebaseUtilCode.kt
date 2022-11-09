package com.example.dudeulimproject.utils

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseUtilCode  {
    companion object{
        fun getFirebaseDatabase() : FirebaseDatabase {
            return FirebaseDatabase.getInstance()
        }
        fun getFirestore() : FirebaseFirestore {
            return Firebase.firestore
        }
    }
}