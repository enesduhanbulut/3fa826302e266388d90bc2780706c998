package com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.duhan.satelliteinfo.features.core.data.AppDatabase
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatelliteDetailEntity
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatelliteItemEntity
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.SatellitePositionEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class DaoTests {

    private lateinit var detailDao: SatelliteDetailDao
    private lateinit var itemDao: SatelliteItemDao
    private lateinit var positionDao: SatellitePositionDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        detailDao = db.satelliteDetailDao()
        itemDao = db.satelliteItemDao()
        positionDao = db.satellitePositionDao()

    }

    @Test
    fun insert_and_read_detail_entity() {
        val satelliteDetailItem = SatelliteDetailEntity(
            1, 2, "12.12.2012", 13, 1455
        )
        detailDao.insert(satelliteDetailItem)
        val dbValue = detailDao.getSatelliteDetail(satelliteDetailItem.id)
        if (satelliteDetailItem == dbValue) {
            assert(true)
        } else {
            assert(false)
        }
    }

    @Test
    fun insert_and_read_item_entity() {
        val satelliteItem = SatelliteItemEntity(
            1, "name", true
        )
        itemDao.insert(satelliteItem)
        val dbValue = itemDao.getSatelliteItem(satelliteItem.id)
        if (satelliteItem == dbValue) {
            assert(true)
        } else {
            assert(false)
        }
    }

    @Test
    fun insert_and_read_position_entity() {
        val satellitePosition = SatellitePositionEntity(
            1, 2.0, 3.0
        )
        positionDao.insert(satellitePosition)
        val dbValue = positionDao.getSatellitePosition(satellitePosition.id)
        if (satellitePosition == dbValue) {
            assert(true)
        } else {
            assert(false)
        }
    }

}