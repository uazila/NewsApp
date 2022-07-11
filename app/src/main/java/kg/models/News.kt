package kg.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class News (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
            val createdAt: Long

) : Serializable