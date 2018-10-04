package com.winnicki.rsstopstories.ext

import android.support.design.widget.Snackbar
import android.view.View

fun View.snack(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, length).show()
}
