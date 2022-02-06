package com.example.nagwainterview.view.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nagwainterview.NagwaApplication
import com.example.nagwainterview.R
import com.example.nagwainterview.base.BaseVBActivity
import com.example.nagwainterview.databinding.ActivityMainBinding
import com.example.nagwainterview.model.FileModel
import com.example.nagwainterview.utils.*
import com.example.nagwainterview.utils.Constants.Companion.FILE_NAME
import com.example.nagwainterview.view.main.adapter.FilesAdapter
import com.example.nagwainterview.viewmodel.main.MainViewModel
import java.io.File
import javax.inject.Inject


class MainActivity : BaseVBActivity<ActivityMainBinding>(), FilesAdapter.OnItemClickListener{

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    private var files: ArrayList<FileModel> = ArrayList()
    private var filesAdapter: FilesAdapter = FilesAdapter(this, files, this)

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
        binding.filesRv.layoutManager = GridLayoutManager(this, 2)
        binding.filesRv.isNestedScrollingEnabled = false
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
            if (it) showProgress()
            else hideProgress()
        })
    }

    override fun onItemClick(model: FileModel, position: Int) {
        val targetFile = File(filesDir, model.name!!)
        if(targetFile.exists()){
            openFileActivity(model)
        } else{
            showConfirmationDialog(model,targetFile,position)
        }
    }

    private fun showConfirmationDialog(model: FileModel, targetFile: File, position: Int) {
        CustomDialogs(this)
            .setPositiveAction {
                downloadFile(model, targetFile, position)
            }
            .showConfirmationDialog(getString(R.string.download_confirmation, model.name))
    }

    private fun downloadFile(model: FileModel, targetFile: File, position: Int) {
        viewModel.downloadFile(model.url, targetFile)
        viewModel.liveDataDownloadFile.observe(this, {
            filesAdapter.notifyItemChanged(position)
            viewModel.resetFileDownloadStatus()
            showSuccessDialog(model)
        })
    }

    private fun showSuccessDialog(model: FileModel) {
        CustomDialogs(this)
            .setPositiveAction {
                openFileActivity(model)
            }
            .showSuccessDialog(getString(R.string.download_complete))
    }

    private fun openFileActivity(model: FileModel){
        when(model.type){
            Constants.VIDEO -> {
                val intent = Intent(this@MainActivity,VideoActivity::class.java)
                intent.putExtra(FILE_NAME, model.name)
                startActivity(intent)
            }
            Constants.PDF -> {
                val intent = Intent(this@MainActivity,PdfActivity::class.java)
                intent.putExtra(FILE_NAME, model.name)
                startActivity(intent)
            }
            else -> showMessage(getString(R.string.file_type_error))
        }
    }
}