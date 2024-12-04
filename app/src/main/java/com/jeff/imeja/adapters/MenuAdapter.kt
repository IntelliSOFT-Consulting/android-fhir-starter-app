package com.jeff.imeja.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeff.imeja.databinding.ItemMenuCardBinding
import com.jeff.imeja.models.MenuItem


class MenuAdapter(
    private val menuItems: List<MenuItem>,
    private val onclick: (MenuItem) -> Unit
) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(private val binding: ItemMenuCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuItem) {
            binding.iconImageView.setImageResource(item.icon)
            binding.titleTextView.text = item.title

            binding.apply {
                iconImageView.apply {
                    setOnClickListener {
                        onclick(item)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMenuCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuItems[position])
    }

    override fun getItemCount(): Int = menuItems.size
}