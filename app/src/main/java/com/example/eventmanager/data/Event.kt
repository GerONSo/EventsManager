package com.example.eventmanager.data

data class Event(var name: String, var type: String, var adminLogin: String,
                 var watchers: Int, var participants: Int, val date: String,
                 var changeable: Boolean = false){
    constructor() : this("", "", "", 0, 0, "")
}

data class NewEvent(val name: String, val type: String,
                    val date: String, val admin: String,
                    val location: String)