package com.duhan.satelliteinfo.features.satellite.data.asset_data_source

data class SatelliteDetailResponse(
    val list: List<SatelliteDetailItem>
)

data class SatelliteDetailItem(
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int,
)