package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.tourroutessaratovregion.adapters.RegionAdapter
import com.sosnowskydevelop.tourroutessaratovregion.adapters.RouteAdapter
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRegionListBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.InjectorUtils
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RegionListViewModel

class RegionListFragment : Fragment() {
    private lateinit var fragmentRegionListBinding: FragmentRegionListBinding

    private val regionListViewModel: RegionListViewModel by viewModels {
        InjectorUtils.provideRegionListViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentRegionListBinding =
            FragmentRegionListBinding.inflate(inflater, container, false)
        return fragmentRegionListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Турмаршруты"

        val regionLayoutManager = LinearLayoutManager(requireContext())
        fragmentRegionListBinding.regionList.layoutManager = regionLayoutManager
        val regionListAdapter = RegionAdapter(
            regions = regionListViewModel.regions,
            fragment = this,
        )
        fragmentRegionListBinding.regionList.adapter = regionListAdapter

        val routeLayoutManager = LinearLayoutManager(requireContext())
        fragmentRegionListBinding.routeList.layoutManager = routeLayoutManager
        val routeListAdapter = RouteAdapter(
            routes = arrayOf(),
            fragment = this,
        )
        fragmentRegionListBinding.routeList.adapter = routeListAdapter

        fragmentRegionListBinding.routeSearch.addTextChangedListener {
            val searchQuery: String = it.toString()
            if (searchQuery.isNotBlank()) {
                fragmentRegionListBinding.regionList.visibility = View.INVISIBLE
                fragmentRegionListBinding.routeList.visibility = View.VISIBLE
                regionListViewModel.updateRoutes(searchQuery = searchQuery)
                if (regionListViewModel.routes.isNotEmpty()) {
                    fragmentRegionListBinding.nothingFound.visibility = View.INVISIBLE
                    fragmentRegionListBinding.routeList.visibility = View.VISIBLE
                    routeListAdapter.routes = regionListViewModel.routes
                    routeListAdapter.notifyDataSetChanged()
                } else {
                    fragmentRegionListBinding.routeList.visibility = View.INVISIBLE
                    fragmentRegionListBinding.nothingFound.visibility = View.VISIBLE
                }
            } else {
                fragmentRegionListBinding.routeList.visibility = View.INVISIBLE
                fragmentRegionListBinding.regionList.visibility = View.VISIBLE
            }
        }
    }
}