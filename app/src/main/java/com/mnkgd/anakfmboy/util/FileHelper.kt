package com.mnkgd.anakfmboy.util

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileHelper(val context:Context) {
    val folderName = "anakfolder"
    val fileName = "dataukur.txt"

    // function untuk bikin file baru/load file jika sudah ada
    private fun getFile(): File {
        val dir = File(context.filesDir, folderName)
        if (!dir.exists()) {
            dir.mkdirs() // bikin folder jika folder belum ada
        }
        return File(dir, fileName)
    }
    fun writeToFile(data: String) {
        try {
            val file = getFile()
            FileOutputStream(file, false).use { output ->
                output.write(data.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readFromFile(): String {
        return try {
            val file = getFile()
            if (!file.exists()) "[]"
            else file.bufferedReader().useLines { it.joinToString("\n") }
        } catch (e: IOException) {
            "[]"
        }
    }

    fun deleteFile(): Boolean {
        return getFile().delete()
    }

    // Menghasilkan string path menuju file
    fun getFilePath(): String {
        return getFile().absolutePath
    }




}
