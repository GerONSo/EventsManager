package com.example.eventmanager.activities

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.eventmanager.MainViewModel
import com.example.eventmanager.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_event.*

class EventActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var viewModel: MainViewModel
    private val menuItems = mutableListOf("My events", "Leisure", "Art", "Volunteering", "Patriotism",
        "Media", "Extreme", "Leadership", "Entrepreneurship", "Prevention", "Sport")
    private val indexes = mutableListOf(R.drawable.calendar, R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
        R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        initViews()
    }

    private fun initViews() {
        initDrawer()
        nv.itemIconTintList = null
        nv.setNavigationItemSelectedListener { menuItem ->
//            menuItem.isChecked = true
            when(menuItem.itemId) {
                228 -> {

                }
                R.id.my_events -> {

                }
                R.id.leisure -> {
                    Toast.makeText(this, "d", Toast.LENGTH_SHORT).show()
                }
                R.id.art -> {
                    Toast.makeText(this, "a", Toast.LENGTH_SHORT).show()
                }
                R.id.volunteering -> {
                    Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
                }
                R.id.patriotism -> {
                    Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
                }
                R.id.media -> {
                    Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
                }
                R.id.extreme -> {
                    Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
                }
                R.id.leadership -> {
                    Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
                }
                R.id.entrepreneurship -> {
                    Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
                }
                R.id.prevention -> {
                    Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
                }
                R.id.sport -> {
                    Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun initDrawer() {
        var navigationView: NavigationView = nv
        var menu = navigationView.menu
        if(true) { //TODO if superuser
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
                //TODO on add click listener
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
            // TODO redraw recycler
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


}
