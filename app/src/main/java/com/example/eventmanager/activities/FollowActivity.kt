package com.example.eventmanager.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.eventmanager.R
import com.example.eventmanager.adapters.MainRecyclerAdapter
import com.example.eventmanager.extensions.parseDate
import com.example.eventmanager.requests.MainViewModel
import kotlinx.android.synthetic.main.activity_follow.*

class FollowActivity : AppCompatActivity() {

    companion object{
        lateinit var name: String
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)
        val event = MainViewModel.getData().value?.get(MainViewModel.currentType!!)
            ?.get(MainRecyclerAdapter.currentlyClicked)!!
        tv_name.text = event.name
        name = tv_name.text.toString()
        tv_organiser.text = event.adminLogin
        val (a, b) = event.date.parseDate()

        tv_date.text = a
        tv_time.text = b
        btn_follow.setOnClickListener {

                    btn_follow.text = "you folowed"
                    btn_follow.setBackgroundColor(getColor(R.color.gay_color_dark))
                }


        }
    }

