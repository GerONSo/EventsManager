package com.example.eventmanager.requests

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eventmanager.data.Event
import com.example.eventmanager.activities.EventActivity
import com.example.eventmanager.activities.FollowActivity
import com.example.eventmanager.activities.LoginActivity
import com.example.eventmanager.data.NewEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MainViewModel : ViewModel() {
    private var data: MutableLiveData<MutableList<MutableList<Event>>> = MutableLiveData()
    init {
        data.value = MutableList( 10 ) { mutableListOf<Event>() }
    }
    private val retrofit = Retrofit.Builder().baseUrl("http://10.0.0.148:5000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val api = retrofit.create(API::class.java)
    val menuItemsList = mutableListOf("My events", "Leisure", "Art", "Volunteering", "Patriotism",
        "Media", "Extreme", "Leadership", "Entrepreneurship", "Prevention", "Sport")
    init {
        if (!LoginActivity.user.isSuperuser)
            menuItemsList.removeAt(0)
    }
    val menuItemsMap = menuItemsList.withIndex().map { Pair(it.value, it.index) }.toMap()
    var currentType: Int? = null
    fun getData(): MutableLiveData<MutableList<MutableList<Event>>> {
        return data
    }


    fun update() {
        val call = api.updateData(Request(menuItemsList[currentType!!]))
        call.enqueue(object : Callback<CustomResponse>{
            override fun onFailure(call: Call<CustomResponse>, t: Throwable) {
                Log.d("Request","$t + kek")
            }
            override fun onResponse(call: Call<CustomResponse>, response: Response<CustomResponse>) {
                currentType?.let {
                    val correctList = MutableList(response.body()!!.datas.size) { Event() }
                    response.body()!!.datas.withIndex().forEach{new ->
                        val splited = new.value.split(',')
                        correctList[new.index] = Event(
                            splited[0], menuItemsList[currentType!!], splited[1],
                            splited[2].toInt(), splited[3].toInt(), splited[4]
                        )
                    }
                    data.value!!.set(it, correctList)
                }
                EventActivity.mainAdapter.notifyDataSetChanged()
            }
        })
    }

    fun createEvent(event: NewEvent){
        val call = api.addEvent(event)
        call.enqueue(object : Callback<AddResponse>{
            override fun onFailure(call: Call<AddResponse>, t: Throwable) {
                Log.d("Request","${t} + kek")
            }

            override fun onResponse(call: Call<AddResponse>, response: Response<AddResponse>) {
                Log.d("Request","${response.body()}")
            }

        })
        update()
    }

    fun addToMy(onItemClickListener: () -> Unit) {
        val call = api.add(FlexRespond(FollowActivity.name))
        call.enqueue(object : Callback<FlexResponse>{
            override fun onFailure(call: Call<FlexResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<FlexResponse>, response: Response<FlexResponse>) {
                Log.d("Glebik","${response.body()}")
                onItemClickListener()
            }

        })
    }

}


