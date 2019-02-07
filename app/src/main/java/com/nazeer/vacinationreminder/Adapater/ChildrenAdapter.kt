package com.nazeer.vacinationreminder.Adapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazeer.vacinationreminder.Commons.Utils
import com.nazeer.vacinationreminder.Data.Child
import com.nazeer.vacinationreminder.R
import kotlinx.android.synthetic.main.child_list_item.view.*


class ChildrenAdapter : RecyclerView.Adapter<MVH>() {
      var items: List<Child> = ArrayList()
    lateinit var childSelectListener: ((Child)->Unit)
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MVH, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_list_item, parent, false)
        return MVH(view,childSelectListener)
    }
}

 class MVH( view: View, listener: (Child)->Unit) : RecyclerView.ViewHolder(view) {
    private val titleTv  = view.child_list_item_name
    private val birthDayTv = view.child_list_item_birth_day
    private lateinit var child:Child

    init {
        view.setOnClickListener {listener.invoke(child)}
    }
    fun bind(child: Child) {
        titleTv.text = child.name
        birthDayTv.text = Utils.formatDate(child.birthDate)
        this.child=child;

    }


}
