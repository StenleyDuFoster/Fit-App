package com.stenleone.fitapp.util.easyToast

import android.content.Context
import android.widget.Toast
import com.stenleone.fitapp.App

val context = App.contextComponent

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun makeToast(message: String) {
    context.toast(message)
}