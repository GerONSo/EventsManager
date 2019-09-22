package com.example.eventmanager.activities

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanager.requests.MainViewModel
import com.example.eventmanager.R
import com.example.eventmanager.adapters.MainRecyclerAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_event.*
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import android.util.Log


class EventActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var viewModel: MainViewModel
    private val menuItems = mutableListOf("My events", "Leisure", "Art", "Volunteering", "Patriotism",
        "Media", "Extreme", "Leadership", "Entrepreneurship", "Prevention", "Sport")
    private val indexes = mutableListOf(R.drawable.calendar, R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
        R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j)
    companion object{
        lateinit var mainAdapter: MainRecyclerAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        mainAdapter = MainRecyclerAdapter{
            startActivity(Intent(this, FollowActivity::class.java))
        }
        mainRecycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@EventActivity)
        }
        swiperefresh.setOnRefreshListener {
            MainViewModel.update()
            mainRecycler.adapter!!.notifyDataSetChanged()
            Log.d("Request","Entered")
            swiperefresh.isRefreshing = false
        }
        initViews()
    }

    private fun initViews() {
        initDrawer()
        nv.itemIconTintList = null
        val mapOfId = mapOf(
            R.id.my_events to 0,
            R.id.leisure to 1,
            R.id.art to 2,
            R.id.volunteering to 3,
            R.id.patriotism to 4,
            R.id.media to 5,
            R.id.extreme to 6,
            R.id.leadership to 7,
            R.id.entrepreneurship to 8,
            R.id.prevention to 9,
            R.id.sport to 10
        )
        toolbar.setTitleTextColor(resources.getColor(R.color.textColorLight))
        if (LoginActivity.user.isSuperuser)
        nv.setNavigationItemSelectedListener { menuItem ->
//            menuItem.isChecked = true
            when(menuItem.itemId) {
                    228 -> { }
                R.id.my_events -> { }
                else -> {
                    MainViewModel.currentType = mapOfId[menuItem.itemId]?.minus(if (LoginActivity.user.isSuperuser) 1 else 0)
                    MainViewModel.update()
                    mainRecycler.adapter!!.notifyDataSetChanged()
                }
            }
            true
        }

    }



    private fun initDrawer() {
        var navigationView: NavigationView = nv
        var menu = navigationView.menu
        if(LoginActivity.user.isSuperuser) {
            menu.add(Menu.NONE, 228, 0, "")
            menuItems.add(0, "Added events")
            indexes.add(0, R.drawable.checklist)
        }
        for(i in 0 until menu.size()) {
            var menuItem: MenuItem = menu.getItem(i)
            menuItem.title = menuItems[i]
            menuItem.icon = getDrawable(indexes[i])
        }
    }

    override fun onResume() {
        super.onResume()
        //TODO update data from server
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.add -> {
                startActivity(Intent(this, AddEventActivity::class.java))
                true
            }
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            MainViewModel.update()
            mainRecycler.adapter!!.notifyDataSetChanged()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}
