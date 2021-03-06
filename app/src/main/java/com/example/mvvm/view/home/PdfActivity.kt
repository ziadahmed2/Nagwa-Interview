package com.example.mvvm.view.home

import android.os.Bundle
import com.example.mvvm.R
import com.example.mvvm.base.BaseVBActivity
import com.example.mvvm.databinding.ActivityPdfBinding
import com.example.mvvm.utils.Constants.Companion.FILE_NAME
import com.example.mvvm.utils.showMessage
import java.io.File

class PdfActivity : BaseVBActivity<ActivityPdfBinding>() {
    override fun getViewBinding(): ActivityPdfBinding {
        return ActivityPdfBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val targetFile = File(filesDir, intent.getStringExtra(FILE_NAME)!!)

        if (targetFile.exists()){
            binding.pdfView.fromFile(targetFile)
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                .spacing(0)
                .load()
        }
        else{
            showMessage(getString(R.string.file_not_found))
        }
    }
}