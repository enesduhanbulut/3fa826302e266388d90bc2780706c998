package com.duhan.satelliteinfo.features.satellite.data.db_data_source.model

import androidx.room.Entity

@Entity(tableName = "satellite_position")
data class SatellitePositionEntity(
    val id: Int = 0,
    val posX: Double = 0.0,
    val posY: Double = 0.0
)