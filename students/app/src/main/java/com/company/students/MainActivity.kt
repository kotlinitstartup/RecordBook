package com.company.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance("https://kursach-a88dc-default-rtdb.europe-west1.firebasedatabase.app")


        database.getReference("students").child("vanya").get().addOnSuccessListener {
            if(it.exists()){
                Log.i("govno", it.child("id").value.toString())
            }
        }.addOnFailureListener{
            Log.i("govno", "13213")
        }
    }
}