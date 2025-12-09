package jetpack

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(version = 2,entities = [User::class, Rider::class])
abstract  class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun riderDao(): RiderDao

    companion object{


        val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Rider (id integer primary key autoincrement,name text,pages integer")
            }

        }



        private var instance : AppDatabase ?= null
        @Synchronized
        fun getDatabase(context: Context): AppDatabase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,"app_database")
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration() //测试环境使用,直接删除原来数据库
                .build().apply {
                    instance = this
                }
        }
    }
}