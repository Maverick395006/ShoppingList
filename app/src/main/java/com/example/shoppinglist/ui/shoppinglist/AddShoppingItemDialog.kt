package com.example.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.databinding.DialogAddShoppingItemBinding
import com.example.shoppinglist.db.entities.ShoppingItem

class AddShoppingItemDialog(context: Context, var onAddDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    private lateinit var binding: DialogAddShoppingItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val amount = binding.etAmount.text.toString()
            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter All Information.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val newItem = ShoppingItem(name, amount.toInt())
                onAddDialogListener.onAddButtonClicked(newItem)
                dismiss()
            }
        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}