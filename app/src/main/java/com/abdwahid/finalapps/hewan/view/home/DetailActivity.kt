package com.abdwahid.finalapps.hewan.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdwahid.finalapps.data.RetrofitClient.BASE_URL
import com.abdwahid.finalapps.databinding.ActivityDetail2Binding
import com.abdwahid.finalapps.hewan.model.DataHewan
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetail2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<DataHewan>(EXTRA_DATA)

        supportActionBar?.title = data?.title
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.apply {
            Glide.with(root)
                .load(BASE_URL + "image/" + data?.image)
                .into(imageDetail)

            tvTitleDetail.text = data?.title
            tvDesc.text = data?.description
        }
    }
}