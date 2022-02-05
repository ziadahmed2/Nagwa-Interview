package com.example.nagwainterview.view.main

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nagwainterview.NagwaApplication
import com.example.nagwainterview.base.BaseVBActivity
import com.example.nagwainterview.databinding.ActivityMainBinding
import com.example.nagwainterview.model.FileModel
import com.example.nagwainterview.utils.GridSpacingItemDecoration
import com.example.nagwainterview.utils.toPx
import com.example.nagwainterview.view.main.adapter.FilesAdapter
import com.example.nagwainterview.viewmodel.main.MainViewModel
import javax.inject.Inject

class MainActivity : BaseVBActivity<ActivityMainBinding>(), FilesAdapter.OnItemClickListener{

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private var files: ArrayList<FileModel> = ArrayList()
    private var filesAdapter: FilesAdapter = FilesAdapter(this,files,this)

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NagwaApplication.applicationComponent?.mainComponent()?.create()?.inject(this)

        initRV()
        getListOfFiles()
        observeProgress()
    }

    private fun initRV(){
        binding.filesRv.layoutManager = GridLayoutManager(this,2)
        binding.filesRv.addItemDecoration(GridSpacingItemDecoration(2, 15.toPx, false))
    }

    private fun getListOfFiles() {
        viewModel.getListOfFiles()
        viewModel.liveDataGetListOfFiles.observe(this, { model ->
            run {
                if (!model.isNullOrEmpty()) {
                    files.addAll(model)
                    binding.filesRv.adapter = filesAdapter
                }
            }
        })
    }

    private fun observeProgress() {
        viewModel.liveDataProgressBar.observe(this, {
            if(it) showProgress()
            else hideProgress()
        })
    }

    override fun onItemClick(model: FileModel) {
        Log.d(TAG, "onItemClick: ${model.name}")
    }
}