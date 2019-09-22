package com.example.eventmanager.data

import kotlin.math.log

data class User(val login: String, val password: String, val isSuperuser: Boolean,
                val name: String, val surname: String) {
    constructor(login: String, password: String,
                isSuperuser: Boolean) : this(login, password, isSuperuser, "", "")
}