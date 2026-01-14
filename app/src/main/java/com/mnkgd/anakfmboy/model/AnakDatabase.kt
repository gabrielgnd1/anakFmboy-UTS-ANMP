package com.mnkgd.anakfmboy.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class AnakDatabase {
    @Database(entities = [Profil::class, Pengukuran::class], version = 1)
    abstract class AnakDatabase : RoomDatabase() {
        abstract fun anakDao(): AnakDao

        companion object {
            @Volatile
            private var instance: AnakDatabase? = null

            private val LOCK = Any()

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    AnakDatabase::class.java,
                    "anakdb" //namae db ne
                ).build()

            operator fun invoke(context: Context) {
                if (instance == null) {
                    synchronized(LOCK) {
                        instance ?: buildDatabase(context).also {
                            instance = it
                        }
                    }
                }
            }
        }

    }
}