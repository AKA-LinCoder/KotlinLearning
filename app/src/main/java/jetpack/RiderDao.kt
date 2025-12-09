package jetpack

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RiderDao {
    @Insert
    fun insertRider(rider: Rider): Int

    @Query("select * from rider")
    fun loadAllRiders(): List<Rider>
}