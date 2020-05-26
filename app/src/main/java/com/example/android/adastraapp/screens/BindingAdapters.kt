package com.example.android.adastraapp.screens

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.adastraapp.database.Boosters
import com.example.android.adastraapp.screens.overview.SpaceAdapter

@BindingAdapter("listData")
fun bindRecycleView(recyclerView: RecyclerView, data: List<Boosters>?){
    val adapter = recyclerView.adapter as SpaceAdapter
    adapter.submitList(data)
}


@BindingAdapter("boosterSerial")
fun TextView.setBoosterSerial(item: Boosters?) {
    item?.let {
        text = item.core_serial
    }
}

@BindingAdapter("boosterStatus")
fun TextView.setBoosterStatus(item: Boosters?) {
    item?.let {
        text = getStatus(item.status)
    }
}

@BindingAdapter("boosterReuseCount")
fun TextView.setBoosterReuseCount(item: Boosters?) {
    item?.let {
        text = item.reuse_count.toString()
    }
}

@BindingAdapter("boosterOriginalLaunch")
fun TextView.setBoosterOriginalLaunch(item: Boosters?) {
    item?.let {
        text = item.original_launch
    }
}