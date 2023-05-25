package com.example.currencyconverter.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogLogOutFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Do you want to change user?")
            .setMessage("If yes, then you'll be taken to the start screen")
            .setPositiveButton("Yes") { dialog, _ ->
                // Обработка нажатия на кнопку OK
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                // Обработка нажатия на кнопку Cancel
                dialog.dismiss()
            }
            .create()

    }
}