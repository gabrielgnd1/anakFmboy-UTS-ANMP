package com.mnkgd.anakfmboy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mnkgd.anakfmboy.model.Pengukuran
import com.mnkgd.anakfmboy.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UkurViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    fun tambahPengukuran(p: Pengukuran) {
        launch(Dispatchers.IO) {
            val db = buildDb(getApplication())
            db.anakDao().insertPengukuran(p)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}