package com.raylabs.jetmovie.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogHelper {

    var builderProvider: ((Context) -> AlertDialog.Builder)? = null

    fun showDialog(
        context: Context,
        title: String,
        message: String,
        positiveText: String = "OK",
        negativeText: String? = null,
        onConfirm: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        val builder = builderProvider?.invoke(context) ?: AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { _, _ -> onConfirm?.invoke() }

        if (negativeText != null) {
            builder.setNegativeButton(negativeText) { _, _ -> onCancel?.invoke() }
        }

        builder.show()
    }
}