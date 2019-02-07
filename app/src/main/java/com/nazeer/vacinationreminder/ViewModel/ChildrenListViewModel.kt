package com.nazeer.vacinationreminder.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nazeer.vacinationreminder.Data.Child
import com.nazeer.vacinationreminder.Data.Repo
import com.nazeer.vacinationreminder.View.AddChildActivity
import com.nazeer.vacinationreminder.View.ChildDetailsActivity

class ChildrenListViewModel(private val repo : Repo):ViewModel() {

    fun getChildren () = repo.getChildren();
    fun startAddingChild(context: Context) {
        context.startActivity(AddChildActivity.getStartIntent(context))
    }

    fun onClickedChild(context: Context,child:Child) {
        context.startActivity(ChildDetailsActivity.getStartIntent(context,child.id))

    }

}