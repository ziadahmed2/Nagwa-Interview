package com.example.mvvm.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources
import com.example.mvvm.R

class SpinnerDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val relativeLayout: RelativeLayout = RelativeLayout(context)
        val progressBar: ProgressBar = ProgressBar(context)
        relativeLayout.addView(progressBar)
        val layoutParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(80.toPx,80.toPx)
        layoutParams.setMargins(6.toPx,6.toPx,6.toPx,6.toPx)
        progressBar.layoutParams = layoutParams

        setContentView(relativeLayout)
        window?.setLayout(80.toPx,80.toPx)
        window?.setBackgroundDrawable(AppCompatResources.getDrawable(context,R.drawable.spinner_progress_dialog))

        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    override fun hide() {
        super.hide()
    }

}
