package com.example.mvvm.view.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.BaseMvvmApplication
import com.example.mvvm.R
import com.example.mvvm.databinding.FileListItemBinding
import com.example.mvvm.model.FileModel
import com.example.mvvm.utils.Constants
import java.io.File

class FilesAdapter(val context: Context, val files: ArrayList<FileModel>, private val listener: OnItemClickListener)
    : RecyclerView.Adapter<FilesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FileListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(files[position]){
                binding.fileName.text = this.name
                when(this.type){
                    Constants.VIDEO ->  Glide.with(context).load(R.drawable.video).into(binding.fileImage)
                    Constants.PDF -> Glide.with(context).load(R.drawable.pdf).into(binding.fileImage)
                    else -> Glide.with(context).load(R.drawable.unknown).into(binding.fileImage)
                }

                val targetFile = File(BaseMvvmApplication.appContext?.filesDir, this.name!!)
                if(targetFile.exists()){
                    binding.fileStatus.setImageResource(R.drawable.ic_downloaded)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return files.size
    }

    inner class ViewHolder(val binding: FileListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init { binding.root.setOnClickListener(this) }
        override fun onClick(v: View?) { listener.onItemClick(files[adapterPosition], adapterPosition) }
    }

    interface OnItemClickListener {
        fun onItemClick(model: FileModel, position: Int)
    }

}