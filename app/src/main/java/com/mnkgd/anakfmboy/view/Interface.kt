package com.mnkgd.anakfmboy.view

import android.view.View

interface UkurListener{
    fun onClick(v: View)
}

interface ProfilAnakListener{
    fun onSave(v: View)
    fun onPickDate(v: View)
    fun onGenderSelect(v: View)
}