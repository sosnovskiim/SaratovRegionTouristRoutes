package com.sosnowskydevelop.tourroutessaratovregion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.tourroutessaratovregion.R
import com.sosnowskydevelop.tourroutessaratovregion.data.Region
import com.sosnowskydevelop.tourroutessaratovregion.databinding.ListItemRegionBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.BUNDLE_KEY_REGION_ID_REGION_LIST_TO_ROUTE_LIST
import com.sosnowskydevelop.tourroutessaratovregion.utilities.REQUEST_KEY_REGION_ID_REGION_LIST_TO_ROUTE_LIST
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RegionListItemViewModel

class RegionAdapter(
        private val regions: Array<Region>,
        private val fragment: Fragment,
) : RecyclerView.Adapter<RegionAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemRegionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemRegionBinding = ListItemRegionBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding = listItemRegionBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = RegionListItemViewModel(regions[position])
        holder.binding.regionName.setOnClickListener {
            fragment.setFragmentResult(
                    requestKey = REQUEST_KEY_REGION_ID_REGION_LIST_TO_ROUTE_LIST,
                    result = bundleOf(BUNDLE_KEY_REGION_ID_REGION_LIST_TO_ROUTE_LIST
                            to regions[position].id)
            )
            fragment.findNavController()
                    .navigate(R.id.action_regionListFragment_to_routeListFragment)
        }
    }

    override fun getItemCount() = regions.size
}