package com.duhan.satelliteinfo.features.home.presentation

import com.duhan.satelliteinfo.features.base.presentation.BaseListItem

data class SatelliteItemModel(
    val id: Int,
    val name: String,
    val active: Boolean,
    ) : BaseListItem
