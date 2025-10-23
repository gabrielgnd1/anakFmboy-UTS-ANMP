package com.mnkgd.anakfmboy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mnkgd.anakfmboy.model.Ukur
import com.mnkgd.anakfmboy.util.FileHelper

class UkurViewModel(application: Application) : AndroidViewModel(application) {


    val ukurLD = MutableLiveData<ArrayList<Ukur>>()
    val ukurLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private val gson = Gson()
    private val fileHelper = FileHelper(getApplication())

    fun readJsonToLog() {
        try {
            val json = fileHelper.readFromFile()
            Log.d("Ukur", "JSON content:\n$json")
        } catch (e: Exception) {
            Log.e("Ukur", "Gagal Membaca: ${e.message}")
        }
    }

    private val listType = object : TypeToken<ArrayList<Ukur>>() {}.type

    fun refresh() {
        loadingLD.value = true
        ukurLoadErrorLD.value = false

        try {
            val json = fileHelper.readFromFile()
            Log.d("Ukur", "print_file_read: $json")

            val list: ArrayList<Ukur> =
                if (json.isBlank()) arrayListOf()
                else gson.fromJson<ArrayList<Ukur>>(json, listType)

            ukurLD.value = list
        } catch (e: Exception) {
            ukurLoadErrorLD.value = true
            ukurLD.value = arrayListOf()
            Log.e("Ukur", "Gagal membaca/parse JSON: ${e.message}", e)
        } finally {
            loadingLD.value = false
        }
    }


    fun saveToFile(usia: String, tinggi: String, berat: String) {
        val list = ukurLD.value ?: arrayListOf()
        list.add(Ukur(berat, tinggi, usia))

        val outJson = gson.toJson(list, listType)
        fileHelper.writeToFile(outJson)
        ukurLD.value = list

        Log.d("Ukur", "Saved data: $outJson")
        Log.d("Ukur", "File path: ${fileHelper.getFilePath()}")
        readJsonToLog()
    }
}