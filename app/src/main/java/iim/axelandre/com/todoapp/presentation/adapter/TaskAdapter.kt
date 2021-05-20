package iim.axelandre.com.todoapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import iim.axelandre.com.todoapp.R
import iim.axelandre.com.todoapp.data.model.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(
    private val taskList: ArrayList<Task>,
    private val onCheckListener: (Int, Boolean) -> Unit,
    private val onDeleteListener: (Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = holder.bind(taskList[position], onCheckListener, onDeleteListener)

    class TaskViewHolder(val taskView: View) : RecyclerView.ViewHolder(taskView) {

        fun bind(task: Task, onCheckListener: (Int, Boolean) -> Unit, onDeleteListener: (Int) -> Unit) {
            taskView.taskName.text = task.name
            taskView.checkBox.isChecked = task.isDone

            //trigger le click sur une checkBox
            itemView.view.setOnClickListener {
                itemView.checkBox.performClick()
                itemView.checkBox.isPressed = true
                itemView.checkBox.invalidate()
                itemView.checkBox.isPressed = false
                itemView.checkBox.invalidate()
            }

            itemView.checkBox.setOnCheckedChangeListener { _, isChecked ->
                onCheckListener(task.id, isChecked)
            }

            itemView.deleteTask.setOnClickListener {
                onDeleteListener(task.id)
            }
        }

    }
}