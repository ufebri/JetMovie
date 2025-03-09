package com.bedboy.jetmovie.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogHelper {

    fun showDialog(
        context: Context,
        title: String,
        message: String,
        positiveText: String = "OK",
        negativeText: String? = null,
        onConfirm: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { _, _ -> onConfirm?.invoke() }

        if (negativeText != null) {
            builder.setNegativeButton(negativeText) { _, _ -> onCancel?.invoke() }
        }

        builder.show()
    }
}