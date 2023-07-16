package com.duhan.satelliteinfo.features.satellite.data.db_data_source.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDomainModel

@Entity(tableName = "satellite_list")
data class SatelliteItemEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val isActive: Boolean
)

fun SatelliteItemEntity.toDomainModel(): SatelliteDomainModel {
    return SatelliteDomainModel(
        id = this.id,
        name = this.name,
        isActive = this.isActive,
    )
}