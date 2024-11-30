package com.abi.flagchallenge

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("HiltApp", "OnCreate")
    }
}