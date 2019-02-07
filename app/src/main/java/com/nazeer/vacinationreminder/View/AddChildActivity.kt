package com.nazeer.vacinationreminder.View

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nazeer.vacinationreminder.ViewModel.AddChildViewModel
import com.nazeer.vacinationreminder.Commons.DependencyManager
import com.nazeer.vacinationreminder.R
import kotlinx.android.synthetic.main.activity_add_child.*
import java.util.Calendar

class AddChildActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(context : Context):Intent{
           return Intent(context, AddChildActivity::class.java)
        }
    }
    private lateinit var viewModel: AddChildViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_child)

       viewModel = ViewModelProviders.of(this,
           DependencyManager.modelViewFactory
       ).get(AddChildViewModel::class.java)
        viewModel.childBirthDayLiveData.observe(this, Observer {
            activity_add_child_b_day.text=it
        })
        viewModel.activityAliveLiveData.observe(this, Observer { if(!it)finish() })
        activity_add_child_name.setText(viewModel.childName)
        activity_add_child_action.setOnClickListener {validateAndAddChild()}
        activity_add_child_b_day.setOnClickListener { openDatePicker() }
        activity_add_child_name.addTextChangedListener(getTextChangListener())

    }

    private fun getTextChangListener(): TextWatcher {
       return object : TextWatcher {
           override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
           }

           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
           }

           override fun afterTextChanged(s: Editable?) {
               viewModel.childName=s.toString()
            }

        }
    }

    private fun validateAndAddChild() {
        if(validate()){
            viewModel.addChild()
        }
    }

    private fun validate(): Boolean {
        val name = activity_add_child_name.text
        val date = activity_add_child_b_day.text

        if(!viewModel.hasValidName()){
            Toast.makeText(this,getString(R.string.empty_name_error),Toast.LENGTH_SHORT).show()
            return false
        }else if (!viewModel.hasValidBirthday()){
            Toast.makeText(this,getString(R.string.empty_calendar_error),Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(this,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.YEAR, year)
                viewModel.setChildBirthDay(calendar)

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}
