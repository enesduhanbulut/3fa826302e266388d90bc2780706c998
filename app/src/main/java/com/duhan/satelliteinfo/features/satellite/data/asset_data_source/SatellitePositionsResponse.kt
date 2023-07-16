package com.duhan.satelliteinfo.features.satellite.data.asset_data_source

data class SatellitePositionsResponse(
    val positions: List<SatellitePositionItem>
)

data class SatellitePositionItem(
    val id: Int,
    val positions: List<PositionItem>
)

data class PositionItem(
    val posX: Double,
    val posY: Double,
)