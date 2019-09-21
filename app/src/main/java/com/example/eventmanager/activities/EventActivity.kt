package com.example.eventmanager.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nv.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            when(menuItem.itemId) {
                R.id.account -> {
                    Toast.makeText(this, "d", Toast.LENGTH_SHORT).show()
                }
                R.id.settings -> {
                    Toast.makeText(this, "a", Toast.LENGTH_SHORT).show()
                }
                R.id.mycart -> {
                    Toast.makeText(this, "x", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        //TODO update data from server
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
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
