/**
 * MainActivity
 * the major Activity includes two majors fragment as MapListFragment and CarsFragment
 * in app as the first view for user
 * ActivityMain bind to this Activity and Fragments showed and replaced by bottomNavigationView
 * * 2022-08-22 23:15
 */
package com.coding.sixt.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.coding.sixt.R
import com.coding.sixt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(CarsFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId)
            {
                R.id.View ->     replaceFragment(CarsFragment())
                R.id.Map  ->     replaceFragment(MapListFragment())
            }
            true
        }
    }
    private fun replaceFragment(fragment : Fragment)
    {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
            fragmentTransaction.commit()
    }
}