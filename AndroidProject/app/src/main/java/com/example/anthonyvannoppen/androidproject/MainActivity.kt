package com.example.anthonyvannoppen.androidproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.anthonyvannoppen.androidproject.fragments.MemeListFragment
import com.example.anthonyvannoppen.androidproject.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, MainFragment.newInstance())
                .commitNow()
        }
    }

    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        menuInflater.inflate(R.menu.maintoolbar,menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_home -> {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, MemeListFragment())
                .addToBackStack(null)
                .commit()
            true
        }



        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

}
