package com.duhan.satelliteinfo.features.satellite.data.asset_data_source

data class SatelliteListResponse(
    val satellites: List<SatelliteListItem>
)
data class SatelliteListItem(
    val id: Int,
    val name: String,
    val isActive: Boolean
)