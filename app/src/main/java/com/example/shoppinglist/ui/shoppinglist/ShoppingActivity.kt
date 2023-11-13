package com.example.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.databinding.ActivityShoppingBinding
import com.example.shoppinglist.db.ShoppingDB
import com.example.shoppinglist.db.entities.ShoppingItem
import com.example.shoppinglist.repositories.ShoppingRepository
import com.example.shoppinglist.ui.shoppinglist.adapter.ShoppingItemAdapter

class ShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ShoppingDB(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        binding.rvShoppingList.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingList.adapter = adapter

        viewModel.getAllItems().observe(this){
            adapter.items = it
            adapter.notifyDataSetChanged()
        }

        binding.fab.setOnClickListener {
            AddShoppingItemDialog(context = this, onAddDialogListener = object:AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.add(item)
                }
            }).show()
        }

    }

}