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
import com.abdwahid.finalapps.databinding.FragmentPantaiBinding
import com.abdwahid.finalapps.wisata.adapter.WisataListAdapter
import com.abdwahid.finalapps.wisata.model.DataWisata
import com.abdwahid.finalapps.wisata.viewmodel.CategoryWisataViewModel

class PantaiFragment : Fragment() {

    private lateinit var viewModel: CategoryWisataViewModel

    private lateinit var _binding: FragmentPantaiBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPantaiBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPantai.layoutManager = LinearLayoutManager(context)

        getDataByCategory(2)
    }

    private fun getDataByCategory(category_id: Int){
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryWisataViewModel::class.java)
        viewModel.getByCategory(category_id).observe(this, Observer {
            val adapter = it.data?.let { it1 -> WisataListAdapter(it1) }
            binding.rvPantai.adapter = adapter

            adapter?.setOnItemClickCallback(object : WisataListAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataWisata) {
                    Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()
                }
            })
        })
    }
}