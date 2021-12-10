package id.ac.ubaya.informatika.a160419044_advnmp_uts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class User(
    val id:String?,
    @SerializedName("user_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String?,
    val status:String?
)

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name = "is_done")
    var is_done:Int,
    @ColumnInfo(name = "todo_date")
    var todo_date:Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}