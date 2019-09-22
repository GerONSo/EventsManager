package com.example.eventmanager.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eventmanager.R
import com.example.eventmanager.data.NewEvent
import com.example.eventmanager.requests.MainViewModel
import kotlinx.android.synthetic.main.add_event_layout.*
import kotlin.concurrent.timer

class AddEventActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_event_layout)
        button_create_event.setOnClickListener {
            val eventName = name.text.toString()
            val eventType = type.text.toString()
            val eventData = data.text.toString()
            val eventAdmin = admin.text.toString()
            val eventLocation = location.text.toString()
            val newEvent = NewEvent(eventName, eventType, eventData, eventAdmin, eventLocation)
            MainViewModel.createEvent(newEvent)
            startActivity(Intent(this, EventActivity::class.java))
        }
    }
}