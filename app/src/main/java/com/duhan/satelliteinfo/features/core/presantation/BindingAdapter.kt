package com.duhan.satelliteinfo.features.core.presantation

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.duhan.satelliteinfo.features.base.presentation.BaseListAdapter
import com.duhan.satelliteinfo.features.base.presentation.BaseListItem

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<BaseListItem>?) {
    if (list == null) return
    val adapter = recyclerView.adapter as BaseListAdapter<BaseListItem, ViewDataBinding>?
    adapter?.setItems(list)
}

@BindingAdapter("setConstraintVisibility")
fun ConstraintLayout.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}