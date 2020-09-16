package com.pawan.bhattavyapar

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        db = Firebase.firestore

        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        db.firestoreSettings = settings
    }
    companion object{
        lateinit var db:FirebaseFirestore
        fun getFireStoreObj():FirebaseFirestore{
            return db
        }
    }
}