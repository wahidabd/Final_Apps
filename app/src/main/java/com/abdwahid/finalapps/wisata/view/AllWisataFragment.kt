package com.abdwahid.finalapps.wisata.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdwahid.finalapps.R
import com.abdwahid.finalapps.databinding.FragmentAllWisataBinding
import com.abdwahid.finalapps.wisata.adapter.WisataListAdapter
import com.abdwahid.finalapps.wisata.model.DataWisata
import com.abdwahid.finalapps.wisata.viewmodel.WisataViewModel

class AllWisataFragment : Fragment() {

    private lateinit var viewModel: WisataViewModel

    private lateinit var _binding: FragmentAllWisataBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentAllWisataBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAllData.layoutManager = LinearLayoutManager(context)

        getDataFromViewModel()
    }

    private fun getDataFromViewModel(){
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(WisataViewModel::class.java)
        viewModel.getAllData().observe(this, Observer {
            val adapter = it.data?.let { it1 -> WisataListAdapter(it1) }
            binding.rvAllData.adapter = adapter

            adapter?.setOnItemClickCallback(object : WisataListAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataWisata) {
                    startActivity(Intent(activity, DetailActivity::class.java).putExtra(DetailActivity.EXTRA_DATA, data))

                    Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()
                }
            })
        })
    }
}