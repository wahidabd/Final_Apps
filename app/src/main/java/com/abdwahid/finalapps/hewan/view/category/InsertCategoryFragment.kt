package com.abdwahid.finalapps.hewan.view.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abdwahid.finalapps.R
import com.abdwahid.finalapps.databinding.FragmentInsertCategoryBinding

class InsertCategoryFragment : Fragment() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var _binding: FragmentInsertCategoryBinding
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentInsertCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CategoryViewModel::class.java)

        binding.topAppBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.button.setOnClickListener {
            addData()
        }
    }

    private fun addData(){
        val title = binding.inputNewCategory.editText?.text.toString()

        var isEmptyField = false

        if (title.isEmpty()){
            binding.inputNewCategory.error = "Tidak boleh kosong"
            isEmptyField = true
        }

        if (!isEmptyField){
            viewModel.addData(title).observe(this, Observer {
                Toast.makeText(context, "Category ditambah", Toast.LENGTH_SHORT).show()

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