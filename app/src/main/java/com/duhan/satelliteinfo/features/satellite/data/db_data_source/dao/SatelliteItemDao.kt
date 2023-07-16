package com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao

import androidx.room.Dao
import com.duhan.satelliteinfo.features.base.data.BaseDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatelliteItemEntity

@Dao
interface SatelliteItemDao : BaseDao<SatelliteItemEntity>{
}