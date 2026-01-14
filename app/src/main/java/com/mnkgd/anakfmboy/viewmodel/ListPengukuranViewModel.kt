package com.mnkgd.anakfmboy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mnkgd.anakfmboy.model.Pengukuran
import com.mnkgd.anakfmboy.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListPengukuranViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    val pengukuranLD = MutableLiveData<List<Pengukuran>>()
    val loadingLD = MutableLiveData<Boolean>()
    val errorLD = MutableLiveData<Boolean>()

    fun refresh() {
        loadingLD.value = true
        errorLD.value = false

        launch(Dispatchers.IO) {
            val db = buildDb(getApplication())
            val list = db.anakDao().getAllPengukuran()
            pengukuranLD.postValue(list)
            loadingLD.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}