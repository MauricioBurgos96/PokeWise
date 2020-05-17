package com.mauricioburgos.pokeyellow.core.utils

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.mauricioburgos.pokeyellow.presentation.view.dialogs.MessageDialog

class Utils {

    companion object {
        inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
        val snack = Snackbar.make(this, message, length)
        snack.show()
    }

    fun displayMessage(tittle: String, message: String, fragmentManager: FragmentManager) {
        val dialogFragment = MessageDialog(tittle, message)
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        dialogFragment.show(fragmentTransaction, MessageDialog::class.simpleName)
    }

    inline fun View.snack(
        message: String,
        length: Int = Snackbar.LENGTH_INDEFINITE,
        f: Snackbar.() -> Unit
    ) {
        val snack = Snackbar.make(this, message, length)
        snack.f()
        snack.show()
    }


    }

    fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
        setAction(action, listener)
        color?.let { setActionTextColor(color) }
    }


}