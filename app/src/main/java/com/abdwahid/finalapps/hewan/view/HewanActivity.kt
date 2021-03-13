package com.abdwahid.finalapps.hewan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.abdwahid.finalapps.R
import com.abdwahid.finalapps.databinding.ActivityHewanBinding
import com.abdwahid.finalapps.hewan.view.category.CategoryFragment
import com.abdwahid.finalapps.hewan.view.data.DataFragment
import com.abdwahid.finalapps.hewan.view.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HewanActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHewanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHewanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = HomeFragment()
        addFragment(fragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    private fun addFragment(fm: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_hewan, fm, fm::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.menu_home -> {
                val fragment = HomeFragment()
                addFragment(fragment)
                true
            }

            R.id.menu_data -> {
                val fragment = DataFragment()
                addFragment(fragment)
                true
            }

            R.id.menu_category -> {
                val fragment = CategoryFragment()
                addFragment(fragment)
                true
            }

            else -> false
        }
    }
}