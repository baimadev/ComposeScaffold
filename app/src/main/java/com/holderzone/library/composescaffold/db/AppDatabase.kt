package com.holderzone.library.composescaffold.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.holderzone.library.composescaffold.db.dao.UserInfoDao


/**
 * room数据库
 * */

@Database(entities = [
    UserInfoModel::class
], version = 1,exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

 abstract fun userInfoDao() : UserInfoDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "holder_store.db")
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    }
                )
                .build()
        }
    }
}

