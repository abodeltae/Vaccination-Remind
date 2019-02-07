package com.nazeer.vacinationreminder.Commons

import android.app.Application
import com.nazeer.vacinationreminder.Data.Repo
import com.nazeer.vacinationreminder.Data.DB
import com.nazeer.vacinationreminder.ViewModel.MyModelViewFactory

object  DependencyManager {
     lateinit  var modelViewFactory : MyModelViewFactory

    fun initDependencies(application : Application){
        val database = DB.getInstance(application)
        Repo.init(database!!)
        val childRepo = Repo.getInstance()
        modelViewFactory =
            MyModelViewFactory(childRepo)
    }


}