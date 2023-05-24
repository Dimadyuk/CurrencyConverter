package com.example.currencyconverter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencyconverter.data.models.UserDbModel

@Database(
    entities = [UserDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var db: AppDataBase? = null
        private const val DB_NAME = "main_db"
        private val LOCK = Any()
        fun getInstance(context: Context): AppDataBase {
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    context, AppDataBase::class.java, DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }
}