package com.mart.composeproduct.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToastMessage(
    @StringRes message: Int
) {
    Toast.makeText(
        this,
        getString(message),
        Toast.LENGTH_SHORT
    ).show()
}