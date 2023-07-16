package com.duhan.satelliteinfo.features.satellite.data.db_data_source.model

import androidx.room.Entity

@Entity(tableName = "satellite_list")
data class SatelliteItemEntity(
    val id: Int,
    val name: String,
    val isActive: Boolean
)