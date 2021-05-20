package iim.axelandre.com.todoapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import iim.axelandre.com.todoapp.data.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert(onConflict = REPLACE)
    fun insertAll(task: List<Task>)

    @Insert
    fun insertTask(task: Task): Long?

    @Delete
    fun delete(task: Task)
}