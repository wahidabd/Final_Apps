package com.abdwahid.finalapps.hewan.view.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdwahid.finalapps.R
import com.abdwahid.finalapps.databinding.FragmentHomeBinding
import com.abdwahid.finalapps.hewan.adapter.HomeAdapter
import com.abdwahid.finalapps.hewan.model.DataHewan

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)
        binding.rvHome.layoutManager = LinearLayoutManager(context)

        viewModel.getAllData().observe(this, Observer {
            val adapter = it.data?.let { it1 -> HomeAdapter(it1) }
            binding.rvHome.adapter = adapter

            adapter?.setOnItemClickCallback(object : HomeAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataHewan) {
                    Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()

                    startActivity(Intent(context, DetailActivity::class.java).putExtra(DetailActivity.EXTRA_DATA, data))
                }
            })
        })
    }
}