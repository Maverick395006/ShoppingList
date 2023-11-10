package com.example.shoppinglist.repositories

import com.example.shoppinglist.db.ShoppingDB
import com.example.shoppinglist.db.entities.ShoppingItem

class ShoppingRepository(private val db: ShoppingDB) {
    suspend fun add(item: ShoppingItem) = db.getShoppingDao().add(item)
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()

}