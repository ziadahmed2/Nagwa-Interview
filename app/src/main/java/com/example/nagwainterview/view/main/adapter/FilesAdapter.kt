package com.example.nagwainterview.view.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwainterview.R
import com.example.nagwainterview.databinding.FileListItemBinding
import com.example.nagwainterview.model.FileModel

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
                if(this.type?.equals("VIDEO", ignoreCase = true) == true){
                    binding.fileImage.setImageResource(R.drawable.video)
                } else {
                    binding.fileImage.setImageResource(R.drawable.pdf)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return files.size
    }

    inner class ViewHolder(val binding: FileListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init { binding.root.setOnClickListener(this) }
        override fun onClick(v: View?) { listener.onItemClick(files[adapterPosition]) }
    }

    interface OnItemClickListener {
        fun onItemClick(model: FileModel)
    }

}