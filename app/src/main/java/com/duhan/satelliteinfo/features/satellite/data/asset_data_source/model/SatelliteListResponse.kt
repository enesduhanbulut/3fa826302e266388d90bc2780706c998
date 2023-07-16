package com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model

import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDomainModel
import com.squareup.moshi.Json

class SatelliteListResponse : ArrayList<SatelliteListItem>()
data class SatelliteListItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "active")
    val isActive: Boolean
)

fun SatelliteListItem.toDomainModel(): SatelliteDomainModel {
    return SatelliteDomainModel(
        id = id,
        name = name,
        isActive = isActive,
    )
}