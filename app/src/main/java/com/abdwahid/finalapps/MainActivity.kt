package com.abdwahid.finalapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdwahid.finalapps.databinding.ActivityMainBinding
import com.abdwahid.finalapps.hewan.view.HewanActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardHewan.setOnClickListener {
            startActivity(Intent(this, HewanActivity::class.java))
        }

        binding.cardWisata.setOnClickListener {
            startActivity(Intent(this, HewanActivity::class.java))
        }
    }
}