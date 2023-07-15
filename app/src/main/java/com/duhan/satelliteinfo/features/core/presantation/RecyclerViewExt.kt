package com.duhan.satelliteinfo.features.core.presantation

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setup(
    adapter: RecyclerView.Adapter<*>,
    layoutManager: RecyclerView.LayoutManager = androidx.recyclerview.widget.LinearLayoutManager(
        context,
        RecyclerView.VERTICAL,
        false
    ),
    hasFixedSize: Boolean = false,
    itemDecoration: RecyclerViewMargin? = null,
    itemAnimator: RecyclerView.ItemAnimator? = null,
) {
    this.adapter = adapter
    this.layoutManager = layoutManager
    this.setHasFixedSize(hasFixedSize)
    itemDecoration?.let { this.addItemDecoration(it) }
    itemAnimator?.let { this.itemAnimator = it }
}
