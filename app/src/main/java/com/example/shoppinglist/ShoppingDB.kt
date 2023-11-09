package com.example.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDB : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

    companion object {

        const val SHOPPING_DATABASE = "shopping_database"

        @Volatile
        private var instance: ShoppingDB? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, ShoppingDB::class.java,
                SHOPPING_DATABASE
            ).build()

    }

}