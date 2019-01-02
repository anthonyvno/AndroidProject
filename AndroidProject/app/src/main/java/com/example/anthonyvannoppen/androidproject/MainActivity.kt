package com.example.anthonyvannoppen.androidproject

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.anthonyvannoppen.androidproject.fragments.MemeAddFragment
import com.example.anthonyvannoppen.androidproject.fragments.MemeListFragment
import com.example.anthonyvannoppen.androidproject.ui.MemeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel :  MemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        viewModel = ViewModelProviders.of(this).get(MemeViewModel::class.java)

        /*if(isNetworkAvailable()){
            Toast.makeText(this, "No internet connection",Toast.LENGTH_SHORT)
        }*/

        supportFragmentManager.beginTransaction()
            .add(R.id.container_main, MemeListFragment())
            .addToBackStack("main")
            .commit()
    }

    /*private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }*/


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //supportActionBar!!.setDisplayShowTitleEnabled(false)
        menuInflater.inflate(R.menu.maintoolbar,menu)
        val act = this
        val item = menu!!.findItem(R.id.action_sort)
        val spinner = item.actionView as Spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(this, R.array.categorieen, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter

        //De keuze in de dropdownmenu zal een nieuw list fragment aanmaken en de gesorteerde lijst meegeven
        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val cat=resources.getStringArray(R.array.categorieen)[position]

                if(cat != "Any"){
                    viewModel.getMemes().observe(act, Observer {
                        val sortedMemes=it!!.filter{ meme ->
                            meme.categorie.contains(cat)
                        }
                        val memeListFragment = MemeListFragment()
                        memeListFragment.sort(sortedMemes)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container_main, memeListFragment)
                            .addToBackStack(null)
                            .commit()
                    })
                }else{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container_main, MemeListFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }


        return true
    }





    //navigatie toolbar
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        /*R.id.action_home -> {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, MemeListFragment())
                .addToBackStack(null)
                .commit()
            true
        }*/
        R.id.action_add -> {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, MemeAddFragment())
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
