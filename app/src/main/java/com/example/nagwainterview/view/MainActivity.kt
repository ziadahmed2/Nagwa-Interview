package com.example.nagwainterview.view

import android.os.Bundle
import com.example.nagwainterview.base.BaseVBActivity
import com.example.nagwainterview.databinding.ActivityMainBinding

class MainActivity : BaseVBActivity<ActivityMainBinding>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainBtn.text = "ziad"
//        showProgress()
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}