package com.example.uselessapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "useful")
data class Useful (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val age: Int
)