package iim.axelandre.com.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name : String,
    var isDone : Boolean,
)
