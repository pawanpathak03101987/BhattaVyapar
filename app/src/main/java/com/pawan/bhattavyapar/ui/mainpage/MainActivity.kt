package com.pawan.bhattavyapar.ui.mainpage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawan.bhattavyapar.R
import com.pawan.bhattavyapar.adapter.ClickListener
import com.pawan.bhattavyapar.adapter.NavigationRVAdapter
import com.pawan.bhattavyapar.adapter.RecyclerTouchListener
import com.pawan.bhattavyapar.data.model.NavigationItemModel
import com.pawan.bhattavyapar.ui.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter

    private var items = arrayListOf(
        NavigationItemModel(android.R.drawable.btn_star_big_on, "Business Profile"),
        NavigationItemModel(android.R.drawable.btn_star_big_on, "Security"),
        NavigationItemModel(android.R.drawable.btn_star_big_on, "Currency"),
        NavigationItemModel(android.R.drawable.btn_star_big_on, "Rate"),


    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)

        // Set the toolbar
        setSupportActionBar(toolbar)

        navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        // # Home Fragment
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Home Fragment")
                        val homeFragment = HomeFragment()
                        homeFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, homeFragment).commit()
                    }
                    1 -> {
                        // # Music Fragment
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Music Fragment")
                        val musicFragment = HomeFragment()
                        musicFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, musicFragment).commit()
                    }
                    2 -> {
                        // # Movies Fragment
                        val bundle = Bundle()
                        bundle.putString("fragmentName", "Movies Fragment")
                        val moviesFragment = HomeFragment()
                        moviesFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_content_id, moviesFragment).commit()
                    }

                }
                // Don't highlight the 'Profile' and 'Like us on Facebook' item row
                if (position != 6 && position != 4) {
                    updateAdapter(position)
                }
                Handler().postDelayed({
                    drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))

        // Setup Recyclerview's Layout
        updateAdapter(0)

        // Set 'Home' as the default fragment when the app starts
        val bundle = Bundle()
        bundle.putString("fragmentName", "Home Fragment")
        val homeFragment = HomeFragment()
        homeFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_content_id, homeFragment).commit()
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                // Triggered once the drawer opens
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()


        // Set Header Image
        navigation_header_img.setImageResource(R.mipmap.facebook)

        // Set background of Drawer
        navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        navigation_rv.layoutManager = LinearLayoutManager(this)
        navigation_rv.setHasFixedSize(true)
    }


    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            // Checking for fragment count on back stack
            if (supportFragmentManager.backStackEntryCount > 0) {
                // Go to the previous fragment
                supportFragmentManager.popBackStack()
            } else {
                // Exit the app
                super.onBackPressed()
            }
        }
    }
}