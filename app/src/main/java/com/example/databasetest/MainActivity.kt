package com.example.databasetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databasetest.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val database = Firebase.database("https://databasetest-bf33b-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private var data = database.reference
    private lateinit var binding : ActivityMainBinding
    private var nama : User = User(null, null)

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.myName = nama
//
//        binding.button.setOnClickListener {
//            val name = binding.textName.text
//            val userId = binding.textId.text.toString()
//
//            val user = User(userId, name.toString())
//            data.child("Users").child(userId).get().addOnSuccessListener {
//                if(it.exists())
//                {
//                    Toast.makeText(this,"User Already Exist",Toast.LENGTH_SHORT).show()
//                }
//                else
//                {
//                    data.child("Users").child(userId).setValue(user)
//                    Toast.makeText(this,"User Added",Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }

//    override fun onCreate(savedInstanceState: Bundle?)
//    {
//        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.myName = nama
//
//        binding.button.setOnClickListener()
//        {
//            val name = binding.textName.text
//            var userId = 1
//            data.child("Users").get().addOnSuccessListener()
//            {
//                if(!it.exists())
//                {
//                    val user = User(userId.toString(), name.toString())
//                    data.child("Users").child(userId.toString()).setValue(user)
//                }
//                else
//                {
//                    for(key in it.children + 1)
//                    {
//                        if(it.child(userId.toString()).exists())
//                        {
//                            userId++
//                        }
//                        else
//                        {
//                            val user = User(userId.toString(), name.toString())
//                            data.child("Users").child(userId.toString()).setValue(user)
//                            break
//                        }
//                    }
//                }
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = nama

        binding.addBtn.setOnClickListener()
        {
            val name = binding.username.text
            var userId:Any? = 1
            data.child("Users").get().addOnSuccessListener()
            {
                if(it.exists())
                {
                    val latestId = it.childrenCount
                    userId = latestId.toInt() + 1
                    userId = if(userId.toString().toInt() < 10) {
                        "U00$userId"
                    } else if (userId.toString().toInt() < 100) {
                        "U0$userId"
                    } else {
                        "U$userId"
                    }
                    val user = User(userId.toString(), name.toString())
                    data.child("Users").child(userId.toString()).setValue(user)
                    displayData()
                }
                else
                {
                    userId = "U001"
                    val user = User(userId.toString(), name.toString())
                    data.child("Users").child(userId.toString()).setValue(user)
                }
            }
        }
    }

    private fun displayData()
    {
        data.child("Users").get().addOnSuccessListener()
        {
            if(it.exists())
            {
                for(allData in it.children)
                {
                    var key = allData.key.toString()
                    for(allInnerData in allData.children)
                    {
                        var value = allInnerData.value.toString()
                        var temp = value
                    }
                    binding.listView.append("$key")
                }
//                var allData : HashMap<String, String> = it.value as HashMap<String, String>
//                allData.forEach { (key, value) -> println("$key: $value") }
//                for((key, value) in allData)
//                {
//                    binding.listView.append("$key., $value")
//                }
            }
        }
    }
}