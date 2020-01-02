package com.example.uselessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var usefulViewModel: UsefulViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize a RecycleView adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = UsefulAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Initialize the ViewModel
        usefulViewModel= ViewModelProvider(this).get(UsefulViewModel::class.java)
        usefulViewModel.allUseful.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setUsefulRecords(it) }
        })



        fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                //TODO : Save a useful record to the database
                data?.let {
                    val useful = Useful(
                        0,
                        it.getStringExtra(AddActivity.EXTRA_NAME),
                        it.getStringExtra(AddActivity.EXTRA_AGE).toInt()
                    )
                    usefulViewModel.insertUseful(useful)
                    Toast.makeText(this, "Record saved", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    companion object{
        const val REQUEST_CODE = 1
    }
}

