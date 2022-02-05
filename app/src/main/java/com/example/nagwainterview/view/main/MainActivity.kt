package com.example.nagwainterview.view.main

import android.os.Bundle
import android.util.Log
import com.example.nagwainterview.NagwaApplication
import com.example.nagwainterview.base.BaseVBActivity
import com.example.nagwainterview.databinding.ActivityMainBinding
import com.example.nagwainterview.viewmodel.main.MainViewModel
import javax.inject.Inject

class MainActivity : BaseVBActivity<ActivityMainBinding>(){

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NagwaApplication.applicationComponent?.mainComponent()?.create()?.inject(this)
        binding.mainBtn.text = "ziad"

        viewModel.getListOfFiles()
        viewModel.liveDataGetListOfFiles.observe(this, { model ->
            run {
                Log.d(TAG, "onCreate: ${model?.size}")
            }
        })

        viewModel.liveDataProgressBar.observe(this, {
            if(it) showProgress()
            else hideProgress()
        })
    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}