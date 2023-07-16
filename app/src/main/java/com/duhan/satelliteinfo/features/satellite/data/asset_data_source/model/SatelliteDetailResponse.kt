package com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model

import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDetailDomainModel
import com.squareup.moshi.Json

class SatelliteDetailResponse : ArrayList<SatelliteDetailItem>()

data class SatelliteDetailItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "cost_per_launch")
    val costPerLaunch: Int,
    @Json(name = "first_flight")
    val firstFlight: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "mass")
    val mass: Int,
)

fun SatelliteDetailItem.toDomainModel(): SatelliteDetailDomainModel {
    return SatelliteDetailDomainModel(
        id = id,
        costPerLaunch = costPerLaunch,
        firstFlight = firstFlight,
        height = height,
        mass = mass,
    )
}