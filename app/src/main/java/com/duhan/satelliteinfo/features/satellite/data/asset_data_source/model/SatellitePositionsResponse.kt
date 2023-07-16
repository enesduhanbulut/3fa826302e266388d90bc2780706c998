package com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model

import com.squareup.moshi.Json

data class SatellitePositionsResponse(
    @Json(name = "list")
    val positions: List<SatellitePositionItem>
)

data class SatellitePositionItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "positions")
    val positions: List<PositionItem>
)

data class PositionItem(
    @Json(name = "posX")
    val posX: Double,
    @Json(name = "posY")
    val posY: Double,
)