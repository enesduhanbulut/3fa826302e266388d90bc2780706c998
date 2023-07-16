package com.duhan.satelliteinfo.features.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteDetailDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteItemDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatellitePositionDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatelliteDetailEntity
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatelliteItemEntity
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatellitePositionEntity

@Database(
    entities = [
        SatelliteItemEntity::class,
        SatelliteDetailEntity::class,
        SatellitePositionEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun satelliteItemDao(): SatelliteItemDao
    abstract fun satelliteDetailDao(): SatelliteDetailDao
    abstract fun satellitePositionDao(): SatellitePositionDao
}