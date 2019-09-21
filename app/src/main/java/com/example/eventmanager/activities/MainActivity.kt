package com.example.eventmanager.activities

import adapter.ChooseTypeAdapter
import adapter.Info
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanager.R

class MainActivity : AppCompatActivity() {

    companion object{
        var CHOOSED_EVENT: Int? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.typeRecycler)
        val chooseTypeAdapter = ChooseTypeAdapter(listOf(Info("kek","flex"),
            Info("flex", "ti pidor"))) {position ->
            Log.d("main","${position}")
            CHOOSED_EVENT = position
        }
        recyclerView.apply {
            adapter = chooseTypeAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }


}
