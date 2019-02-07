package com.nazeer.vacinationreminder.Commons

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initRepoDependencies();
    }

    private fun initRepoDependencies() {
        DependencyManager.initDependencies(this)
    }

}