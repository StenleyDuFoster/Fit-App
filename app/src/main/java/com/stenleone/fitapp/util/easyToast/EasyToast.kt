package com.stenleone.fitapp.util.easyToast

import android.widget.Toast
import com.stenleone.fitapp.koin.application.App

fun makeToast(message: String) {
    Toast
        .makeText(
            App.contextComponent,
            message,
            Toast.LENGTH_SHORT
        )
        .show()
}