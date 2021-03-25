package com.lumi.moviecata.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lumi.moviecata.data.source.local.entity.MovieEntity
import com.lumi.moviecata.data.source.local.entity.SeriesEntity

@Database(entities = [MovieEntity::class, SeriesEntity::class],
    version = 1,
    exportSchema = false)
abstract class CataDatabase : RoomDatabase() {
    abstract fun cataDao(): CataDao

    companion object {

        @Volatile
        private var INSTANCE: CataDatabase? = null

        fun getInstance(context: Context): CataDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    CataDatabase::class.java,
                    "Catalogue.db").build()
            }
    }
}