package com.example.eventmanager.requests

import com.example.eventmanager.data.Event
import com.example.eventmanager.data.NewEvent
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {

    @POST("/Data")
    fun updateData(@Body type: Request) : Call<CustomResponse>

    @POST("/Suck")
    fun addEvent(@Body event: NewEvent) : Call<AddResponse>
}

data class Request(val type: String)

data class CustomResponse(val datas: List<String>)
data class AddResponse(val suckass: Boolean)
