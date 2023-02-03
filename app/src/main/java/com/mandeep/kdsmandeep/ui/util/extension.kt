package com.mandeep.kdsmandeep.ui.util

import android.text.Editable
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*

/**
 * App Name: KDS Mandeep
 * Package: com.mandeep.kdsmandeep.ui.util
 * By: Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 03 Feb, 2023
 **/


infix fun TextInputLayout.showErrorMessage(message: String) {
    isEnabled = false
    error = message
    CoroutineScope(Dispatchers.IO).launch {
        delay(750)
        withContext(Dispatchers.Main) {
            isEnabled = true
            error = null
        }
    }
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)


fun View.showMessage(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}
