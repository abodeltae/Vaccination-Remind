package com.nazeer.vacinationreminder.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazeer.vacinationreminder.Adapater.ChildrenAdapter
import com.nazeer.vacinationreminder.ViewModel.ChildrenListViewModel
import com.nazeer.vacinationreminder.Data.Child
import com.nazeer.vacinationreminder.Commons.DependencyManager
import com.nazeer.vacinationreminder.R
import kotlinx.android.synthetic.main.children_list_activity.*

class ChildListActivity : AppCompatActivity() {

     private lateinit var  childrenListModelView: ChildrenListViewModel
    private lateinit var adapter: ChildrenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.children_list_activity)
         childrenListModelView=
            ViewModelProviders.of(this,
                DependencyManager.modelViewFactory
            ).get(ChildrenListViewModel::class.java)
        adapter = ChildrenAdapter()
        adapter.childSelectListener={child -> childrenListModelView.onClickedChild(this,child) }
        children_list_activity_recycler_view.layoutManager = LinearLayoutManager(this)
        children_list_activity_recycler_view.adapter = adapter
        children_list_activity_add.setOnClickListener {childrenListModelView.startAddingChild(this)}
        childrenListModelView.getChildren().observe(this, Observer{updateUI(it)})

    }



    private fun updateUI(childrenList: List<Child>){
        adapter.items=childrenList
        adapter.notifyDataSetChanged()
    }
}
