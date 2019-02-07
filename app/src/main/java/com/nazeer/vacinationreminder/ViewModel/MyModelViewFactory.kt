package com.nazeer.vacinationreminder.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nazeer.vacinationreminder.Data.Repo


class MyModelViewFactory(private val repo: Repo) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ChildrenListViewModel::class.java -> ChildrenListViewModel(repo) as T
            AddChildViewModel::class.java -> AddChildViewModel(repo) as T
            ChildDetailViewModel::class.java -> ChildDetailViewModel(repo)as T
            else -> super.create(modelClass)
        }
    }
}