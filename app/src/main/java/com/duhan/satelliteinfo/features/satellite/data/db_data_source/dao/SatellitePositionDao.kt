package com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao

import androidx.room.Dao
import androidx.room.Query
import com.duhan.satelliteinfo.features.base.data.BaseDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatellitePositionEntity

@Dao
interface SatellitePositionDao : BaseDao<SatellitePositionEntity> {
    @Query("SELECT * FROM satellite_position WHERE id = :id")
    fun getSatellitePosition(id: Int): SatellitePositionEntity

    @Query("SELECT * FROM satellite_position")
    fun getSatellitePositionList(): List<SatellitePositionEntity>

    @Query("DELETE FROM satellite_position")
    fun deleteAll()
}