package com.example.mvvm.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.mvvm.R

class CustomDialogs(context: Context) : View.OnClickListener {

    private val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
    private val dialogView: View = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null, false)
    private var alertDialog: AlertDialog? = null
    private var positiveAction: (() -> Unit?)? = null
    private var negativeAction: (() -> Unit?)? = null

    init {
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        alertDialog = dialogBuilder.create()
        alertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogView.findViewById<Button>(R.id.btnPositive).setOnClickListener(this)
        dialogView.findViewById<TextView>(R.id.btnCancel).setOnClickListener(this)
    }

    fun showSuccessDialog(message: String) {
        dialogView.findViewById<TextView>(R.id.tvMessage).text = message
        dialogView.findViewById<ImageView>(R.id.ivImage).setImageResource(R.drawable.icn_popup_success)
        dialogView.findViewById<ImageView>(R.id.ivImage).visibility = View.VISIBLE
        dialogView.findViewById<Button>(R.id.btnPositive).setText(R.string.ok)
        dialogView.findViewById<TextView>(R.id.btnCancel).visibility = View.GONE
        alertDialog?.show()
    }

    fun showSuccessDialogWithoutIcon(message: String) {
        dialogView.findViewById<TextView>(R.id.tvMessage).text = message
        dialogView.findViewById<ImageView>(R.id.ivImage).visibility = View.GONE
        dialogView.findViewById<Button>(R.id.btnPositive).setText(R.string.ok)
        dialogView.findViewById<TextView>(R.id.btnCancel).visibility = View.GONE
        alertDialog?.show()
    }

    fun showSuccessDialog(message: String, positiveLabel: Int, drawableId: Int) {
        dialogView.findViewById<TextView>(R.id.tvMessage).text = message
        dialogView.findViewById<ImageView>(R.id.ivImage).setImageResource(drawableId)
        dialogView.findViewById<ImageView>(R.id.ivImage).visibility = View.VISIBLE
        dialogView.findViewById<Button>(R.id.btnPositive).setText(positiveLabel)
        dialogView.findViewById<TextView>(R.id.btnCancel).visibility = View.GONE
        alertDialog?.show()
    }

    fun showConfirmationDialog(message: String,
                               positiveButtonName: String? = null,
                               cancelButtonName: String? = null) {
        dialogView.findViewById<TextView>(R.id.tvMessage).text = message
        dialogView.findViewById<ImageView>(R.id.ivImage).visibility = View.GONE
        dialogView.findViewById<TextView>(R.id.tvTitle).setText(R.string.download)
        dialogView.findViewById<TextView>(R.id.tvTitle).visibility = View.VISIBLE
        if (!positiveButtonName.isNullOrEmpty())
            dialogView.findViewById<Button>(R.id.btnPositive).text = positiveButtonName
        else
            dialogView.findViewById<Button>(R.id.btnPositive).setText(R.string.ok)
        if (!cancelButtonName.isNullOrEmpty())
            dialogView.findViewById<TextView>(R.id.btnCancel).text = cancelButtonName
        else
            dialogView.findViewById<TextView>(R.id.btnCancel).setText(R.string.cancel)

        alertDialog?.show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnPositive -> {
                positiveAction?.invoke()
                alertDialog?.dismiss()
            }
            R.id.btnCancel -> {
                negativeAction?.invoke()
                alertDialog?.dismiss()
            }
        }
    }

    fun setPositiveAction(action: () -> Unit): CustomDialogs {
        positiveAction = action
        return this
    }

    fun setNegativeAction(action: () -> Unit): CustomDialogs {
        negativeAction = action
        return this
    }
}