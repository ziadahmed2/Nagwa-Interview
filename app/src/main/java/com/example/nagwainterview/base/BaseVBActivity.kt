package com.example.nagwainterview.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.nagwainterview.utils.SpinnerDialog

abstract class BaseVBActivity<B : ViewBinding> : AppCompatActivity(), BasePresenterListener {

    lateinit var binding: B
    lateinit var spinnerDialog: SpinnerDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        spinnerDialog = SpinnerDialog(this)
        setContentView(binding.root)
    }

    abstract fun getViewBinding(): B

    override fun showProgress() {
        spinnerDialog.show()
    }

    override fun hideProgress() {
        spinnerDialog.hide()
    }

}