package com.example.nagwainterview.view.main

import android.os.Bundle
import android.widget.MediaController
import com.example.nagwainterview.R
import com.example.nagwainterview.base.BaseVBActivity
import com.example.nagwainterview.databinding.ActivityVideoBinding
import com.example.nagwainterview.utils.Constants.Companion.FILE_NAME
import com.example.nagwainterview.utils.showMessage
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