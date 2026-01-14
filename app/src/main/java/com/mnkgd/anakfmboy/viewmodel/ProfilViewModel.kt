package com.mnkgd.anakfmboy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mnkgd.anakfmboy.model.AnakDatabase
import com.mnkgd.anakfmboy.model.Profil
import com.mnkgd.anakfmboy.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfilViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val profilLD = MutableLiveData<Profil>()

    fun refresh() {
        launch(Dispatchers.IO) {
            val db = buildDb(getApplication())
            val data = db.anakDao().getProfil()
            profilLD.postValue(data)
        }
    }

    fun simpanProfil(profil: Profil) {
        launch(Dispatchers.IO) {
            val db = buildDb(getApplication())
            db.anakDao().insertProfil(profil)
        }
    }
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}