package com.example.currencyconverter.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogLogOutFragment : DialogFragment() {
    private var dialogListener: DialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Do you want to change user?")
            .setMessage("If yes, then you'll be taken to the start screen")
            .setPositiveButton("Yes") { dialog, _ ->
                dialogListener?.onDialogPositiveClick()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }

    fun setDialogListener(listener: DialogListener) {
        dialogListener = listener
    }

    interface DialogListener {
        fun onDialogPositiveClick()
    }
}