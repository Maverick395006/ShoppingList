package com.example.shoppinglist.ui.shoppinglist

import com.example.shoppinglist.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item:ShoppingItem)
}