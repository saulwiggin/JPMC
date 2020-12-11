package com.saulwiggin.jpmc.ui.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView
import com.saulwiggin.jpmc.R
import com.saulwiggin.jpmc.database.DatabaseAlbum
import com.saulwiggin.jpmc.databinding.ListItemBinding

class AlbumsAdapter : RecyclerView.Adapter<AlbumsViewHolder>() {

    var result: List<DatabaseAlbum> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val withDataBinding: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumsViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumsViewHolder(withDataBinding)
    }


    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.viewDataBinding.also{
            it.result = result[position]
        }
    }

    override fun getItemCount() = result.size
}

class AlbumsViewHolder(val viewDataBinding: ListItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.list_item
    }
}
