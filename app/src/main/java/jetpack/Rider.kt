package jetpack

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rider(var name: String,var pages:Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}