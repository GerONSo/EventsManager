package com.example.eventmanager

data class Event(var name: String, var type: String, var adminLogin: String, var watchers: Int, var participants: Int)