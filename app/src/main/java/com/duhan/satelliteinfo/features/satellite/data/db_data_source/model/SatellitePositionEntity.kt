package com.duhan.satelliteinfo.features.satellite.data.db_data_source.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellite_position")
data class SatellitePositionEntity(
    @PrimaryKey
    val id: Int = 0,
    val posX: Double = 0.0,
    val posY: Double = 0.0
)