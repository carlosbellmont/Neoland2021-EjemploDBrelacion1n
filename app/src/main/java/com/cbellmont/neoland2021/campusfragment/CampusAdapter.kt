package com.cbellmont.neoland2021.campusfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cbellmont.neoland2021.databinding.ItemCampusBinding
import com.cbellmont.neoland2021.model.entity.Campus

class CampusAdapter: RecyclerView.Adapter<CampusAdapter.CampusViewHolder>() {

    private var campusList =  listOf<Campus>()

    class CampusViewHolder(val itemBinding: ItemCampusBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampusViewHolder {
        val itemBinding = ItemCampusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CampusViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CampusViewHolder, position: Int) {
        val campus = campusList[position]
        holder.itemBinding.ivPhoto.setImageResource(campus.photoId)

        holder.itemBinding.tvName.text = campus.name

    }

    override fun getItemCount(): Int {
        return campusList.size
    }

    fun updateData(campusList : List<Campus>){
        this.campusList = campusList
        notifyDataSetChanged()
    }

}
