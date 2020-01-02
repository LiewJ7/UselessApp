package com.example.uselessapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        buttonSave.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser(){
        if(TextUtils.isEmpty(editTextName.text)){
            editTextName.setError(getString(R.string.error_value_required))
            return
        }

        if(TextUtils.isEmpty(editTextAge.text)){
            editTextAge.setError(getString(R.string.error_value_required))
            return
        }

        val name = editTextName.text.toString()
        val age = editTextAge.text.toString()

        val intent = Intent()
        intent.putExtra(EXTRA_NAME, name)
        intent.putExtra(EXTRA_AGE, age)

        setResult(Activity.RESULT_OK, intent)

        finish()
    }
    companion object{
        const val EXTRA_NAME = "name"
        const val EXTRA_AGE = "age"
    }

}
