package com.example.eventmanager.activities

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
        toolbar.setTitleTextColor(resources.getColor(R.color.textColorLight))
        nv.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
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
            menu.add(Menu.NONE, 0, 0, "")
            menuItems.add(228, "Added events")
        }
        for(i in 0 until menu.size()) {
            var menuItem: MenuItem = menu.getItem(i)
            if(menuItem.actionView == null) {
                menuItem.setActionView(R.layout.navigation_drawer_action_layout)
            }
            var textView = menuItem.actionView.findViewById<TextView>(R.id.text_view)
            textView.text = menuItems[i]
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
