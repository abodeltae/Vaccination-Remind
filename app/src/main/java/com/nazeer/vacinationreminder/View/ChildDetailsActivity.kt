package com.nazeer.vacinationreminder.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazeer.vacinationreminder.Adapater.VaccinationAdapter
import com.nazeer.vacinationreminder.Commons.DependencyManager
import com.nazeer.vacinationreminder.Commons.Utils
import com.nazeer.vacinationreminder.Data.Child
import com.nazeer.vacinationreminder.Data.Vaccination
import com.nazeer.vacinationreminder.R
import com.nazeer.vacinationreminder.ViewModel.ChildDetailViewModel
import kotlinx.android.synthetic.main.activity_child_details.*

class ChildDetailsActivity : AppCompatActivity() {
    private lateinit var adapter: VaccinationAdapter

    companion object {
        private const val CHILD_ID="EXTRA_CHILD_ID"

        fun getStartIntent(context: Context, childId:Long): Intent {
            val intent = Intent(context,ChildDetailsActivity::class.java)
            intent.putExtra(CHILD_ID,childId)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_details)
        val childId :Long = intent.getLongExtra(CHILD_ID,-1)
        val viewModel  = ViewModelProviders.of(this, DependencyManager.modelViewFactory).get(ChildDetailViewModel::class.java)
        adapter = VaccinationAdapter()
        activity_child_details_recycler_view.layoutManager=LinearLayoutManager(this)
        activity_child_details_recycler_view.adapter=adapter;
        viewModel.childId = childId
        viewModel.child.observe(this, Observer { updateUiWithChild(it)})
        viewModel.vaccinations.observe(this, Observer { updateUiWithVaccinations(it)})


    }

    private fun updateUiWithVaccinations(it: List<Vaccination>?) {
        adapter.items= it!!
        adapter.notifyDataSetChanged()
    }

    private fun updateUiWithChild(child: Child?) {
        if(child == null)return
        activity_child_details_name.text=child.name
        activity_child_details_birth_day.text = Utils.formatDate(child.birthDate)
    }
}
