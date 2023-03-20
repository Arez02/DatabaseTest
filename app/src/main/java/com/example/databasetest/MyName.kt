package com.example.databasetest

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(var userId: String? = null, var name: String? = null){}