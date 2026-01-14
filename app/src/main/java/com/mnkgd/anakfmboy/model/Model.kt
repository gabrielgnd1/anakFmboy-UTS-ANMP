package com.mnkgd.anakfmboy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profil")
data class Profil(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "nama")
    var nama: String,
    @ColumnInfo(name = "tanggal_lahir")
    var tanggalLahir: String,
    @ColumnInfo(name = "jenis_kelamin")
    var jenisKelamin: String
)

@Entity(tableName = "pengukuran")
data class Pengukuran(
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0,
    @ColumnInfo(name = "berat_badan")
    var beratBadan: Int,
    @ColumnInfo(name = "tinggi_badan")
    var tinggiBadan: Int,
    @ColumnInfo(name = "usia")
    var usia: Int
)