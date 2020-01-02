package com.example.uselessapp

import androidx.lifecycle.LiveData

class UsefulRepository(private val usefulDao: UsefulDao) {

    //Maintain a copy of the LiveData
    val allUseful : LiveData<List<Useful>> = usefulDao.getAllUseful()

    //A function to insert useful record
    suspend fun insertUseful(useful: Useful){
        usefulDao.insertUseful(useful)
    }
}