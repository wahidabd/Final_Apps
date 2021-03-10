package com.abdwahid.finalapps.wisata.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdwahid.finalapps.databinding.FragmentGunungBinding
import com.abdwahid.finalapps.wisata.adapter.WisataListAdapter
import com.abdwahid.finalapps.wisata.model.DataWisata
import com.abdwahid.finalapps.wisata.viewmodel.CategoryWisataViewModel

class GunungFragment : Fragment() {

    private lateinit var viewModel: CategoryWisataViewModel

    private lateinit var _binding: FragmentGunungBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGunungBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGunung.layoutManager = LinearLayoutManager(context)

        getDataByCategory(1)
    }

    private fun getDataByCategory(category_id: Int){
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryWisataViewModel::class.java)
        viewModel.getByCategory(category_id).observe(this, Observer {
            val adapter = it.data?.let { it1 -> WisataListAdapter(it1) }
            binding.rvGunung.adapter = adapter

            adapter?.setOnItemClickCallback(object : WisataListAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataWisata) {
                    Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()
                }
            })
        })
    }
}