package com.coding.sixt.fragments

import android.os.Bundle
import android.view.View
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

            when(it.itemId){

                R.id.View ->
                {
                    replaceFragment(CarsFragment())
                }
                R.id.Map  ->
                {
                    replaceFragment(MapListFragment())
                }

                else ->{
                }

            }

            true

        }
    }
    private fun replaceFragment(fragment : Fragment){

            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
            fragmentTransaction.commit()




    }
}