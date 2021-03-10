package com.abdwahid.finalapps.wisata.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abdwahid.finalapps.data.RetrofitClient.BASE_URL
import com.abdwahid.finalapps.databinding.ActivityDetailBinding
import com.abdwahid.finalapps.wisata.model.DataWisata
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<DataWisata>(EXTRA_DATA)

        Glide.with(binding.root)
            .load(BASE_URL + "image/"+ data?.image)
            .into(binding.imageWisataDetail)

        binding.tvWisataDetailTitle.text = data?.title
        binding.tvWisataDetailDesc.text = data?.description
        binding.topAppBar.title = data?.title

        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }
    }
}