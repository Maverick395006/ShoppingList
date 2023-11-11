package com.example.shoppinglist.ui.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.coroutines.launch

class ShoppingItemAdapter(var items: List<ShoppingItem>, private val viewModel: ShoppingViewModel) :
    RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val itemBinding =
            ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(itemBinding.root)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.binding.apply {
            tvName.text = currentShoppingItem.name
            tvAmount.text = currentShoppingItem.amount.toString()

            ivDelete.setOnClickListener {
                viewModel.delete(currentShoppingItem)
            }

            ivPlus.setOnClickListener {
                currentShoppingItem.amount++
                viewModel.add(currentShoppingItem)
            }

            ivMinus.setOnClickListener {
                if (currentShoppingItem.amount > 0) {
                    currentShoppingItem.amount--
                    viewModel.delete(currentShoppingItem)
                }
            }
        }
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ShoppingItemBinding = ShoppingItemBinding.bind(itemView)
    }

}