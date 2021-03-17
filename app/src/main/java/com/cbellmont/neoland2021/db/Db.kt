package com.cbellmont.neoland2021.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cbellmont.neoland2021.R
import com.cbellmont.neoland2021.model.dao.DbStatusDao
import com.cbellmont.neoland2021.model.dao.RegisteredUserDao
import com.cbellmont.neoland2021.model.dao.StudentDao
import com.cbellmont.neoland2021.model.entity.DbStatus
import com.cbellmont.neoland2021.model.entity.RegisteredUser
import com.cbellmont.neoland2021.model.entity.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [RegisteredUser::class, Student::class, DbStatus::class], version = 2)
abstract class Db : RoomDatabase() {

    abstract fun registeredUserDao(): RegisteredUserDao
    abstract fun studentDao(): StudentDao
    abstract fun dbStatusDao(): DbStatusDao

    companion object {

        @Volatile
        private var INSTANCE: Db? = null

        fun getDatabase(context: Context): Db {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return INSTANCE as Db
                }
                val roomBuilder =
                    Room.databaseBuilder(context.applicationContext, Db::class.java, "database-db")
                roomBuilder.addCallback(getCallback())
                INSTANCE = roomBuilder.build()
                return INSTANCE as Db
            }
        }

        private fun getCallback(): RoomDatabase.Callback {
            return object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    CoroutineScope(Dispatchers.IO).launch {
                        INSTANCE?.registeredUserDao()?.insert(RegisteredUser("carlos@neoland.com"))

                        INSTANCE?.studentDao()?.insert(Student("Carlos", "carlos@neoland.com", photoId = R.mipmap.myself))
                        INSTANCE?.studentDao()?.insert(Student("Belén", "carlos@neoland.com", photoId = R.mipmap.estudiante_chica))
                        INSTANCE?.studentDao()?.insert(Student("Elena", "carlos@neoland.com", photoId = R.mipmap.estudiante_femenina))
                        INSTANCE?.studentDao()?.insert(Student("Sergi", "carlos@neoland.com", photoId = R.mipmap.estudiante_chico))


                        INSTANCE?.dbStatusDao()?.insert(DbStatus(0, true))
                    }
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                }
            }
        }
    }
}