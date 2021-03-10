package com.abdwahid.finalapps.wisata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abdwahid.finalapps.databinding.ActivityWisataBinding
import com.abdwahid.finalapps.wisata.adapter.ViewPagerAdapter
import com.abdwahid.finalapps.wisata.view.AllWisataFragment
import com.abdwahid.finalapps.wisata.view.GunungFragment
import com.abdwahid.finalapps.wisata.view.PantaiFragment

class WisataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWisataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWisataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setTabLayout()
    }

    private fun setTabLayout() {
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.addFragment(AllWisataFragment(), "All Data")
        pagerAdapter.addFragment(GunungFragment(), "Gunung")
        pagerAdapter.addFragment(PantaiFragment(), "Pantai")

        binding.viewPager.adapter = pagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}