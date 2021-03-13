package com.abdwahid.finalapps.hewan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdwahid.finalapps.databinding.ItemListCategoryBinding
import com.abdwahid.finalapps.hewan.model.DataCategory

class CategoryListAdapter(private val list: ArrayList<DataCategory>) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemListCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: DataCategory){
            binding.tvCategoryTitle.text = data.category_name
            binding.categoryId.text = data.id

            binding.tvCategoryTitle.setOnClickListener{
                onItemClickCallback.onItemClicked(data)
            }

            binding.ivEditCategory.setOnClickListener{
                onItemClickCallback.onItemEdit(data)
            }

            binding.ivDeleteCategory.setOnClickListener{
                onItemClickCallback.onItemDelete(data)
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: DataCategory)
        fun onItemDelete(data: DataCategory)
        fun onItemEdit(data: DataCategory)
    }
}