package com.example.uselessapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsefulDao {
    @Insert
    suspend fun insertUseful(useful: Useful)

    @Query("SELECT * FROM useful")
    fun getAllUseful(): LiveData<List<Useful>>

    @Query("SELECT * FROM useful WHERE id = :useful_id")
    fun getAUseful(useful_id: Int): Useful

    @Update
    suspend fun updateUseful(useful: Useful)

    @Delete
    suspend fun deleteUseful(useful: Useful)
}