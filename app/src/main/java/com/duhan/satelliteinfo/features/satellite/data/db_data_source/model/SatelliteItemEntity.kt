package com.duhan.satelliteinfo.features.satellite.data.db_data_source.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "satellite_list")
data class SatelliteItemEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val isActive: Boolean
)