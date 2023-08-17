package com.example.todolist6

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todolist6.databinding.ItemTaskBinding

class TaskAdapter(
    private val list: List<Task>,
    private val onClick: (Task) -> Unit,
    private val onLongClick: (Task) -> Unit
) : Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun onBind(task: Task) {
            binding.checkBox.isChecked = task.isDone
            binding.tvTitle.text = task.title
            itemView.setOnClickListener {
                onClick(task)
            }
            itemView.setOnLongClickListener {
                onLongClick(task)
                true
            }
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                task.isDone = isChecked
            }
        }
    }
}