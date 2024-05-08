package com.example.imtahan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imtahan.databinding.ItemCardBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val modelList = arrayListOf<TaskModel>()
    lateinit var clickDelete: (TaskModel) -> Unit

    inner class TaskViewHolder(val itemTaskBinding: ItemCardBinding) :
        RecyclerView.ViewHolder(itemTaskBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = modelList[position]

        when (item.cetinlik) {
            Cetinlik.ORTA -> holder.itemTaskBinding.imageView.setImageResource(R.drawable.yello)
            Cetinlik.RAHAT -> holder.itemTaskBinding.imageView.setImageResource(R.drawable.green)
            else -> holder.itemTaskBinding.imageView.setImageResource(R.drawable.red)
        }

        holder.itemTaskBinding.textViewtaskadi.text = item.taskName
        holder.itemTaskBinding.textView2songun.visibility = if (item.sonGun) View.VISIBLE else View.GONE
        holder.itemTaskBinding.trashcan.setOnClickListener {
            clickDelete(item)
        }


    }

    fun updateMyList(list: ArrayList<TaskModel>) {
        modelList.clear()
        modelList.addAll(list)
        notifyDataSetChanged()
    }
}