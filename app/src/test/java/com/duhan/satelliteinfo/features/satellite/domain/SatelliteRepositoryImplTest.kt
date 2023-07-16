package com.duhan.satelliteinfo.features.satellite.domain

import app.cash.turbine.test
import com.duhan.satelliteinfo.features.base.domain.RefreshControl
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.AssetsDataSource
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model.SatelliteListItem
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model.SatelliteListResponse
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteDetailDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteItemDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatellitePositionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.time.ExperimentalTime

class SatelliteRepositoryImplTest {

    private lateinit var satelliteRepository: SatelliteRepository
    private val satelliteItemDao = mock(SatelliteItemDao::class.java)
    private val satelliteDetailDao = mock(SatelliteDetailDao::class.java)
    private val satellitePositionDao = mock(SatellitePositionDao::class.java)
    private val assetDataSource = mock(AssetsDataSource::class.java)

    @Before
    fun setUp() {
        satelliteRepository = SatelliteRepositoryImpl(
            Dispatchers.Unconfined,
            satellitePositionDao,
            satelliteItemDao,
            satelliteDetailDao,
            assetDataSource,
            RefreshControl.ONE_TIME
        )
    }

    @OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
    @Test
    fun `on first request calls asset data source next request comes from database`() = runTest {
        val satelliteListResponse = SatelliteListResponse()
        satelliteListResponse.add(SatelliteListItem(1, "name", true))
        Mockito.`when`(assetDataSource.getSatelliteList()).thenReturn(satelliteListResponse)
        Mockito.`when`(satelliteItemDao.getSatelliteList()).thenReturn(emptyList())
        satelliteRepository.getSatelliteList().test {
            Mockito.verify(satelliteItemDao).insertAll(Mockito.anyList())
            Mockito.verify(assetDataSource).getSatelliteList()
            cancelAndIgnoreRemainingEvents()
        }
        satelliteRepository.getSatelliteList().test {
            Mockito.verify(satelliteItemDao).insertAll(Mockito.anyList())
            Mockito.verify(satelliteItemDao, Mockito.times(2)).getSatelliteList()
            Mockito.verify(assetDataSource).getSatelliteList()
            cancelAndIgnoreRemainingEvents()
        }
        assert(true)
    }

}