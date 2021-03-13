package com.abdwahid.finalapps.hewan.view.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdwahid.finalapps.R
import com.abdwahid.finalapps.databinding.FragmentUpdateCategoryBinding


class UpdateCategoryFragment : Fragment() {

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var viewModel: CategoryViewModel
    private lateinit var _binding: FragmentUpdateCategoryBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUpdateCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)

        binding.topAppBar.title = "Update Data"
        binding.button.setOnClickListener {
            updateData()
        }

        binding.topAppBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

    }
    private fun updateData(){
        val data = arguments?.getString(EXTRA_DATA)
        val id = data.toString()
        val title = binding.inputNewCategory.editText?.text.toString()

        var isEmptyField = false

        if (title.isEmpty()){
            binding.inputNewCategory.error = "Tidak boleh kosong"
            isEmptyField = true
        }

        if (!isEmptyField){
            viewModel.updateData(id, title).observe(this, Observer {
                Toast.makeText(context, "Data diupdate", Toast.LENGTH_SHORT).show()

                val fragment = CategoryFragment()
                fragmentManager?.beginTransaction()?.apply {
                    replace(R.id.frame_hewan, fragment, CategoryFragment::class.java.simpleName)
                    addToBackStack(null)
                    commit()
                }
            })
        }
    }

}