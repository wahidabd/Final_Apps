package com.abdwahid.finalapps.hewan.view.data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdwahid.finalapps.R
import com.abdwahid.finalapps.databinding.FragmentDataBinding
import com.abdwahid.finalapps.hewan.adapter.DataListAdapter
import com.abdwahid.finalapps.hewan.model.DataHewan
import com.abdwahid.finalapps.hewan.view.home.HomeViewModel

class DataFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var viewModel: DataViewModel
    private lateinit var _binding: FragmentDataBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDataBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.title = "Data"

        binding.topAppBar.setOnMenuItemClickListener(this)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DataViewModel::class.java)
        binding.rvData.layoutManager = LinearLayoutManager(context)

        viewModel.getAllData().observe(this, Observer {
            val adapter = it.data?.let { it1 -> DataListAdapter(it1) }
            binding.rvData.adapter = adapter

            adapter?.setOnItemCallback(object : DataListAdapter.OnItemClickCallback{
                override fun onItemClicked(dataHewan: DataHewan) {
                    Toast.makeText(context, dataHewan.title, Toast.LENGTH_SHORT).show()
                }

                override fun onEdit(dataHewan: DataHewan) {
                    val mBundle = Bundle()
                    val fragment = UpdataDataFragment()
                    mBundle.putString(UpdataDataFragment.EXTRA_ID, dataHewan.id)
                    fragment.arguments = mBundle

                    fragmentManager?.beginTransaction()?.apply {
                        replace(R.id.frame_hewan, fragment, UpdataDataFragment::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }

                }

                override fun onDelete(dataHewan: DataHewan) {
                    viewModel.deleteData(dataHewan.id.toString()).observe(this@DataFragment, Observer {
                        Toast.makeText(context, "${dataHewan.title} berhasil di hapus", Toast.LENGTH_SHORT).show()
                        val fragment = DataFragment()
                        fragmentManager?.beginTransaction()?.apply {
                            replace(R.id.frame_hewan, fragment, DataFragment::class.java.simpleName)
                            addToBackStack(null)
                            commit()
                        }
                    })
                }

            })
        })
    }

    private fun addFragment(fm: Fragment, fmManager: FragmentManager?){
        fmManager?.beginTransaction()?.apply {
            replace(R.id.frame_hewan, fm, fm::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_data_add -> {
                val fm = InsertDataFragment()
                val manager = fragmentManager
                addFragment(fm, manager)
                true
            }

            else -> false
        }
    }
}