package com.sosnowskydevelop.tourroutessaratovregion.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.tourroutessaratovregion.data.Route
import com.sosnowskydevelop.tourroutessaratovregion.databinding.ListItemRouteBinding
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
    }

    override fun getItemCount() = routes.size
}