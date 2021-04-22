package com.sosnowskydevelop.tourroutessaratovregion.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.tourroutessaratovregion.adapters.RegionAdapter
import com.sosnowskydevelop.tourroutessaratovregion.databinding.FragmentRegionListBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.InjectorUtils
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RegionListViewModel

class RegionListFragment : Fragment() {
    private lateinit var fragmentRegionListBinding: FragmentRegionListBinding

    private val regionListViewModel: RegionListViewModel by viewModels {
        InjectorUtils.provideRegionListViewModelFactory()
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

        val linearLayoutManager = LinearLayoutManager(requireContext())
        fragmentRegionListBinding.regionList.layoutManager = linearLayoutManager

        val regionListAdapter = RegionAdapter(
            regions = regionListViewModel.regions,
            fragment = this,
        )
        fragmentRegionListBinding.regionList.adapter = regionListAdapter
    }
}