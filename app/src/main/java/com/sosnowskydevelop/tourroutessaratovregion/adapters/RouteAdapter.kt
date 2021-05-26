package com.sosnowskydevelop.tourroutessaratovregion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.tourroutessaratovregion.R
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.databinding.ListItemRouteBinding
import com.sosnowskydevelop.tourroutessaratovregion.utilities.BUNDLE_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL
import com.sosnowskydevelop.tourroutessaratovregion.utilities.REQUEST_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL
import com.sosnowskydevelop.tourroutessaratovregion.viewmodels.RouteListItemViewModel

class RouteAdapter(
    var routes: Array<Route>,
    private val fragment: Fragment,
) : RecyclerView.Adapter<RouteAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemRouteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemRouteBinding = ListItemRouteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding = listItemRouteBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = RouteListItemViewModel(routes[position])
        holder.binding.routeName.setOnClickListener {
            fragment.setFragmentResult(
                requestKey = REQUEST_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL,
                result = bundleOf(
                    BUNDLE_KEY_ROUTE_ID_ROUTE_LIST_TO_ROUTE_DETAIL
                            to routes[position].id
                )
            )
            try {
                fragment.findNavController()
                    .navigate(R.id.action_routeListFragment_to_routeDetailFragment)
            } catch (e: IllegalArgumentException) {
                fragment.findNavController()
                    .navigate(R.id.action_routeSearchFragment_to_routeDetailFragment)
            }
        }
    }

    override fun getItemCount() = routes.size
}