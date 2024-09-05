package com.ag.project

import android.app.Application
import com.ag.project.di.initKoin
import org.koin.android.ext.koin.androidContext

class NewsApplication :Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@NewsApplication)
        }
    }
}