package com.mnkgd.anakfmboy.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Profil::class, Pengukuran::class], version = 1)
abstract class AnakDatabase : RoomDatabase() {
    abstract fun anakDao(): AnakDao

    companion object {
        @Volatile
        private var instance: AnakDatabase? = null
        private val LOCK = Any()


         fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AnakDatabase::class.java,
                "anakdb"
            ).build()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}