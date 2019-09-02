package com.example.mohamedashour.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mohamedashour.weatherapp.data.db.daos.MovieDao
import com.example.mohamedashour.weatherapp.data.db.entities.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun movieDao() : MovieDao

    companion object {

        private var sInstance: AppDatabase? = null

        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                  sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "example")
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build()
            }
            return sInstance!!
        }
    }
}