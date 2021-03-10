package com.abdwahid.finalapps.wisata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdwahid.finalapps.data.RetrofitClient.BASE_URL
import com.abdwahid.finalapps.databinding.ItemListWisataBinding
import com.abdwahid.finalapps.wisata.model.DataWisata
import com.bumptech.glide.Glide

class WisataListAdapter(private val list: ArrayList<DataWisata>) : RecyclerView.Adapter<WisataListAdapter.WisataViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val binding = ItemListWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WisataViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class WisataViewHolder(private val binding: ItemListWisataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataWisata){
            Glide.with(binding.root)
                .load(BASE_URL + "image/" + data.image)
                .into(binding.imageWisata)

            binding.tvWisataTitle.text = data.title

            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(data)
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: DataWisata)
    }
}