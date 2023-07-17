package com.duhan.satelliteinfo.features.core.presantation

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.duhan.satelliteinfo.R


fun RecyclerView.setup(
    adapter: RecyclerView.Adapter<*>,
    layoutManager: RecyclerView.LayoutManager = androidx.recyclerview.widget.LinearLayoutManager(
        context,
        RecyclerView.VERTICAL,
        false
    ),
    hasFixedSize: Boolean = false,
    spaceModel: SpaceModel? = null,
    @DrawableRes dividerId: Int = R.drawable.rc_divider,
    itemAnimator: RecyclerView.ItemAnimator? = null,
) {
    DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        .apply {
            setDrawable(
                ContextCompat.getDrawable(
                    context,
                    dividerId
                )!!
            )
            addItemDecoration(this)
        }
    spaceModel?.let { addItemDecoration(SpacingItemDecorator(it)) }
    this.adapter = adapter
    this.layoutManager = layoutManager
    this.setHasFixedSize(hasFixedSize)
    itemAnimator?.let { this.itemAnimator = it }
}
