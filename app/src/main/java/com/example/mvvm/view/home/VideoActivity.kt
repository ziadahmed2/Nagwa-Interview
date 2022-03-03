package com.example.mvvm.view.home

import android.os.Bundle
import android.widget.MediaController
import com.example.mvvm.R
import com.example.mvvm.base.BaseVBActivity
import com.example.mvvm.databinding.ActivityVideoBinding
import com.example.mvvm.utils.Constants.Companion.FILE_NAME
import com.example.mvvm.utils.showMessage
import java.io.File

class VideoActivity : BaseVBActivity<ActivityVideoBinding>() {
    override fun getViewBinding(): ActivityVideoBinding {
        return ActivityVideoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val targetFile = File(filesDir, intent.getStringExtra(FILE_NAME)!!)
        if (targetFile.exists()){
            val mediaController = MediaController(this)
            binding.videoView.setVideoPath(targetFile.absolutePath)
            mediaController.setAnchorView(binding.videoView)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.start()
        } else {
            showMessage(getString(R.string.file_not_found))
        }
    }
}