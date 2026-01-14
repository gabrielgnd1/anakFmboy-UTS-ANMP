package com.mnkgd.anakfmboy.util

import android.content.Context
import com.mnkgd.anakfmboy.model.AnakDatabase


val DB_NAME = "anakdb"

fun buildDb(context: Context): AnakDatabase {
    val db = AnakDatabase.buildDatabase(context)
    return db
}