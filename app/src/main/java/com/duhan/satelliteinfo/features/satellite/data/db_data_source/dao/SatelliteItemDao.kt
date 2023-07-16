package com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao

import androidx.room.Dao
import androidx.room.Query
import com.duhan.satelliteinfo.features.base.data.BaseDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatelliteItemEntity

@Dao
interface SatelliteItemDao : BaseDao<SatelliteItemEntity> {
    @Query("SELECT * FROM satellite_list WHERE id = :id")
    fun getSatelliteItem(id: Int): SatelliteItemEntity
    @Query("SELECT * FROM satellite_list")
    fun getSatelliteList(): List<SatelliteItemEntity>

    @Query("DELETE FROM satellite_list")
    fun deleteAll()
}