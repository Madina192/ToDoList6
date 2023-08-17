package com.example.todolist6

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.todolist6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        adapter = TaskAdapter(viewModel.getAllTasks(), this::onClick, this::onLongClick)
        binding.recyclerView.adapter = adapter
        initClickers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initClickers() {
        with(binding) {
            btnSave.setOnClickListener {
                if (etTask.text.toString().isNotEmpty()) {
                    viewModel.saveTask(etTask.text.toString())
                    etTask.text.clear()
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun onClick(task: Task) {
        viewModel.markTaskAsDone(task)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onLongClick(task: Task) {
        val alertDialogDelete = AlertDialog.Builder(this)
        alertDialogDelete.setMessage(getString(R.string.delete_message))

        alertDialogDelete.setPositiveButton(getString(R.string.yes)) { _, _ ->
            viewModel.removeTask(task)
            adapter.notifyDataSetChanged()
        }

        alertDialogDelete.setNegativeButton(getString(R.string.no)) { dialog, _ ->
            dialog?.cancel()
        }

        alertDialogDelete.create().show()
    }
}