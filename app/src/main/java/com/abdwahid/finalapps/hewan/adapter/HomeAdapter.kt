package com.abdwahid.finalapps.hewan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdwahid.finalapps.data.RetrofitClient.BASE_URL
import com.abdwahid.finalapps.databinding.ListItemHomeBinding
import com.abdwahid.finalapps.hewan.model.DataHewan
import com.bumptech.glide.Glide

class HomeAdapter(private val list: ArrayList<DataHewan>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(private val binding: ListItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataHewan){
            Glide.with(binding.root)
                .load(BASE_URL + "image/" + data.image)
                .into(binding.imageHome)
            
            binding.tvHomeTittle.text = data.title
            
            binding.root.setOnClickListener { 
                onItemClickCallback.onItemClicked(data)
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: DataHewan)
    }
}