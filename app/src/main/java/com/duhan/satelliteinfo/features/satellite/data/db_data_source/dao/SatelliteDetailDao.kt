package com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao

import androidx.room.Dao
import androidx.room.Query
import com.duhan.satelliteinfo.features.base.data.BaseDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatelliteDetailEntity

@Dao
interface SatelliteDetailDao : BaseDao<SatelliteDetailEntity> {
    @Query("SELECT * FROM satellite_detail WHERE id = :id")
    fun getSatelliteDetail(id: Int): SatelliteDetailEntity

    @Query("SELECT * FROM satellite_detail")
    fun getSatelliteDetailList(): List<SatelliteDetailEntity>

    @Query("DELETE FROM satellite_detail")
    fun deleteAll()
}