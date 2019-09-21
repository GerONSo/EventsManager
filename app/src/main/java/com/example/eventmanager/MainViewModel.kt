package com.example.eventmanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var data: MutableLiveData<List<List<Event>>> = MutableLiveData()

    fun getData(): MutableLiveData<List<List<Event>>> {
        return data
    }
}