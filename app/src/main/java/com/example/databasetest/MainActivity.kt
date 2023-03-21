package com.example.databasetest

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.databasetest.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.database.ktx.snapshots
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val database = Firebase.database("https://databasetest-bf33b-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private var data = database.reference
    private lateinit var binding : ActivityMainBinding
    private var nama : User = User(null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = nama

        binding.button.setOnClickListener {
            val name = binding.textName.text
            val userId = binding.textId.text.toString()

            val user = User(userId, name.toString())

            data.child("Users").child(userId).setValue(user)
        }
    }
}