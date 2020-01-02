package com.example.uselessapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsefulViewModel (application: Application) : AndroidViewModel(application) {
    //maintain a reference to the repository
    private val usefulRepository: UsefulRepository

    //maintain a copy of the useful record
    val allUseful : LiveData<List<Useful>>

    init {
        val usefulDao = UsefulDatabase.getDatabase(application, viewModelScope).usefulDao()

        usefulRepository = UsefulRepository(usefulDao)
        allUseful = usefulRepository.allUseful
    }

    //create a coroutine function to insert data in background thread
    fun insertUseful(useful: Useful) = viewModelScope.launch {
        usefulRepository.insertUseful(useful)
    }
}