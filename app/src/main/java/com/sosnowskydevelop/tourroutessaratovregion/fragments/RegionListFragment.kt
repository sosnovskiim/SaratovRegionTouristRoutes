package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.tourroutessaratovregion.R
import com.sosnowskydevelop.tourroutessaratovregion.adapters.RegionAdapter
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRegionListBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.InjectorUtils
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RegionListViewModel

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
        inflater.inflate(R.menu.menu_route_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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