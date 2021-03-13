package com.abdwahid.finalapps.hewan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdwahid.finalapps.databinding.ItemListDataBinding
import com.abdwahid.finalapps.hewan.model.DataHewan

class DataListAdapter(private val list: ArrayList<DataHewan>) : RecyclerView.Adapter<DataListAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ItemListDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataHewan){
            binding.tvCategoryTitle.text = data.title

            binding.tvCategoryTitle.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }

            binding.ivEditCategory.setOnClickListener {
                onItemClickCallback.onEdit(data)
            }

            binding.ivDeleteCategory.setOnClickListener {
                onItemClickCallback.onDelete(data)
            }
        }
    }

    fun setOnItemCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(dataHewan: DataHewan)
        fun onEdit(dataHewan: DataHewan)
        fun onDelete(dataHewan: DataHewan)
    }
}