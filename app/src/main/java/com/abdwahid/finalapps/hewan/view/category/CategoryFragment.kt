package com.abdwahid.finalapps.hewan.view.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdwahid.finalapps.R
import com.abdwahid.finalapps.databinding.FragmentCategoryBinding
import com.abdwahid.finalapps.hewan.adapter.CategoryListAdapter
import com.abdwahid.finalapps.hewan.model.DataCategory

class CategoryFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var viewModel: CategoryViewModel

    private lateinit var _binding: FragmentCategoryBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCategory.layoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)

        binding.topAppBar.setOnMenuItemClickListener(this)

        getData()
    }

    private fun getData() {
        viewModel.getData().observe(this, Observer {
            val adapter = it.data?.let { it1 -> CategoryListAdapter(it1) }
            binding.rvCategory.adapter = adapter

            adapter?.setOnItemClickCallback(object : CategoryListAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataCategory) {
                    Toast.makeText(context, data.category_name, Toast.LENGTH_SHORT).show()
                }

                override fun onItemDelete(data: DataCategory) {
                    viewModel.deleteData(data.id.toString()).observe(viewLifecycleOwner, Observer {
                        Toast.makeText(context, "${data.category_name} Dihapus", Toast.LENGTH_SHORT).show()
                        val fragment = CategoryFragment()
                        val fmManager = fragmentManager
                        addFragment(fragment, fmManager)
                    })
                }

                override fun onItemEdit(data: DataCategory) {
                    val fragment = UpdateCategoryFragment()
                    val mBundle = Bundle()
                    mBundle.putString(UpdateCategoryFragment.EXTRA_DATA, data.id)
                    fragment.arguments = mBundle

                    val fmManager = fragmentManager
                    addFragment(fragment, fmManager)
                }
            })
        })
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.menu_category_add -> {
                val fragment = InsertCategoryFragment()
                val fmManager = fragmentManager
                addFragment(fragment, fmManager)

                true
            }

            else -> false
        }
    }

    private fun addFragment(fragment: Fragment, fmManager: FragmentManager?){
        fmManager?.beginTransaction()?.apply {
            replace(R.id.frame_hewan, fragment, fragment::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }

}