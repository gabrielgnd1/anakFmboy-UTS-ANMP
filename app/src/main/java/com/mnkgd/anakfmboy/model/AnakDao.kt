package com.mnkgd.anakfmboy.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface AnakDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfil(vararg profil: Profil)

    @Update
    fun updateProfil(profil: Profil)

    @Query("SELECT * FROM profil WHERE id = 1")
    fun getProfil(): Profil

    //pengukuran
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPengukuran(pengukuran: Pengukuran)

    @Query("SELECT * FROM pengukuran")
    fun getAllPengukuran(): List<Pengukuran>
}