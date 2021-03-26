package com.sosnowskydevelop.tourroutessaratovregion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.tourroutessaratovregion.data.Region
import com.sosnowskydevelop.tourroutessaratovregion.databinding.ListItemRegionBinding
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RegionListItemViewModel

class RegionAdapter(private val regions: Array<Region>) : RecyclerView.Adapter<RegionAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemRegionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemRegionBinding = ListItemRegionBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding = listItemRegionBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = RegionListItemViewModel(regions[position])
    }

    override fun getItemCount() = regions.size
}