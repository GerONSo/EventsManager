package com.example.eventmanager.extensions

import android.util.Log
import java.util.*

fun String.parseDate() : Pair<String, String> {
    // "12.01.19.16.18
    val splited = this.split('.')
    val a = splited[0] + '.' + splited[1] + '.' + splited[2]
    val b = splited[3] + '.' + splited[4]
    return Pair(a, b)

}