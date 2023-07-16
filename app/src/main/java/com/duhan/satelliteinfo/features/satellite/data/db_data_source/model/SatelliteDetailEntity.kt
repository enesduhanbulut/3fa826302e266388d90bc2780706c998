package com.duhan.satelliteinfo.features.satellite.data.db_data_source.model

import androidx.room.Entity

@Entity(tableName = "satellite_detail")
data class SatelliteDetailEntity(
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int,
)

