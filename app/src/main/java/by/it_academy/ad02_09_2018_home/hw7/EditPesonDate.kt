package by.it_academy.ad02_09_2018_home.hw7

import android.content.Context

interface EditPesonDate {
    fun saveData(position: Int?, context: Context?)
    fun removeData(position: Int?, context: Context?)
}