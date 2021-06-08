package com.sosnowskydevelop.saratovregiontouristroutes.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.saratovregiontouristroutes.R
import com.sosnowskydevelop.saratovregiontouristroutes.adapters.RegionAdapter
import com.sosnowskydevelop.saratovregiontouristroutes.databinding.FragmentRegionListBinding
import com.sosnowskydevelop.saratovregiontouristroutes.utilities.InjectorUtils
import com.sosnowskydevelop.saratovregiontouristroutes.viewmodels.RegionListViewModel

class RegionListFragment : Fragment() {
    private lateinit var fragmentRegionListBinding: FragmentRegionListBinding

    private val regionListViewModel: RegionListViewModel by viewModels {
        InjectorUtils.provideRegionListViewModelFactory(context = requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_route_annotation_and_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_annotation -> {
                findNavController().navigate(R.id.action_regionListFragment_to_annotationFragment)
                true
            }
            R.id.action_search -> {
                findNavController().navigate(R.id.action_regionListFragment_to_routeSearchFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Турмаршруты"

        val regionLayoutManager = LinearLayoutManager(requireContext())
        fragmentRegionListBinding.regionList.layoutManager = regionLayoutManager
        val regionListAdapter = RegionAdapter(
            regions = regionListViewModel.regions,
            fragment = this,
        )
        fragmentRegionListBinding.regionList.adapter = regionListAdapter
    }
}