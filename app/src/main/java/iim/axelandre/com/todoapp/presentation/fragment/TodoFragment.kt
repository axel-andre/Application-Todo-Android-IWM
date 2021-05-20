package iim.axelandre.com.todoapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import iim.axelandre.com.todoapp.domain.SharedPreferencesUtils
import iim.axelandre.com.todoapp.R
import iim.axelandre.com.todoapp.data.model.Task
import iim.axelandre.com.todoapp.presentation.adapter.TaskAdapter
import iim.axelandre.com.todoapp.presentation.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_todo.*

/**
 * A simple [Fragment] subclass.
 * Use the [TodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodoFragment : Fragment() {

    private lateinit var viewModel: TodoViewModel
    private var taskList: ArrayList<Task> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoTitle.text = getString(R.string.hello, SharedPreferencesUtils.getFirstname(), SharedPreferencesUtils.getLastame())
        Toast.makeText(this.activity, getString(R.string.connected_as, SharedPreferencesUtils.getFirstname(), SharedPreferencesUtils.getLastame()), Toast.LENGTH_SHORT).show()

        context?.let {
            viewModel = TodoViewModel(it)
        }

        val layoutManager = LinearLayoutManager(this.requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        todoRecyclerView.layoutManager = layoutManager
        todoRecyclerView.adapter = TaskAdapter(taskList, {id, isChecked -> onCheckTask(id, isChecked)}, {id -> deleteTask(id)})

        todoNewTaskButton.setOnClickListener {
            val taskName: String = todoNewTaskInput.text.toString()
            if (taskName.isNotBlank()) {
                val newTask = Task(name = taskName, isDone = false)

                saveTask(newTask)

                todoNewTaskInput.text = null
            }
        }

        getTaskList()
    }

    private fun onCheckTask(id: Int, isCheck: Boolean) {

        taskList.firstOrNull { it.id == id }?.isDone = isCheck
    }

    private fun saveTaskList(taskList: List<Task>) {
        viewModel.insertTaskList(taskList)
    }

    private fun saveTask(task: Task) {
        viewModel.insertTask(task){newTask -> addTaskToList(newTask)}
    }

    private fun addTaskToList(task: Task) {
        taskList.add(task)
        todoRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun deleteTask(id: Int) {
        taskList.firstOrNull { it.id == id }?.let {
            viewModel.deleteTask(it)
            taskList.remove(it)
            todoRecyclerView.adapter?.notifyDataSetChanged()
        }

    }

    private fun getTaskList() {
        viewModel.getTaskList{taskList -> refreshDataset(taskList)}
    }

    private fun refreshDataset(taskList: List<Task>?) {
        taskList?.let {
            this.taskList.clear()
            this.taskList.addAll(it)
        }
        todoRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        // PUtile pour sauvegarder l'état de tâches
        saveTaskList(taskList)
    }

}