package com.nazeer.vacinationreminder.Adapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazeer.vacinationreminder.Commons.Utils
import com.nazeer.vacinationreminder.Data.Vaccination
import com.nazeer.vacinationreminder.R
import kotlinx.android.synthetic.main.vaccination_list_item.view.*


class VaccinationAdapter (): RecyclerView.Adapter<VaccinationVH>() {
     var items: List<Vaccination> = ArrayList()
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VaccinationVH, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccinationVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vaccination_list_item, parent, false)
        return VaccinationVH(view)
    }
}

class VaccinationVH(view: View ): RecyclerView.ViewHolder(view) {
    private val titleTv  = view.vaccination_list_item_name
    private val vaccinationDateTv = view.vaccination_list_item_date
    private lateinit var vaccination: Vaccination


    fun bind(vaccination: Vaccination) {
        titleTv.text = vaccination.name
        vaccinationDateTv.text = Utils.formatDate(vaccination.time)
        this.vaccination=vaccination;

    }


}
