package com.example.android.adastraapp.screens.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.adastraapp.database.Boosters
import com.example.android.adastraapp.databinding.ItemListBinding

class SpaceAdapter(val clickListener: SpaceListener) : ListAdapter<Boosters, SpaceAdapter.SpaceHolder>(SpaceDiffCallback()) {


    override fun onBindViewHolder(holder: SpaceHolder, position: Int) {

        val item = getItem(position)
        holder.bind(clickListener,item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaceHolder {
        return SpaceHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }


    class SpaceHolder (val binding: ItemListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: SpaceListener, item: Boosters) {
            binding.boosters = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

    }

}


class SpaceDiffCallback : DiffUtil.ItemCallback<Boosters>() {
    override fun areItemsTheSame(oldItem: Boosters, newItem: Boosters): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Boosters, newItem: Boosters): Boolean {
        return oldItem.core_serial == newItem.core_serial
    }
}

class SpaceListener(val clickListener: (boosterId: String) -> Unit) {
    fun onClick(boost: Boosters) = clickListener(boost.core_serial)
}