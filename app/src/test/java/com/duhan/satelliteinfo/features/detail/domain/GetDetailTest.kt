package com.duhan.satelliteinfo.features.detail.domain

import app.cash.turbine.test
import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDetailDomainModel
import com.duhan.satelliteinfo.features.satellite.domain.model.toUIModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import kotlin.time.ExperimentalTime

class GetDetailTest {

    private lateinit var satelliteRepository: SatelliteRepository

    private lateinit var getDetail: GetDetail

    @Before
    fun setUp() {
        satelliteRepository = Mockito.mock(SatelliteRepository::class.java)
        getDetail = GetDetail(satelliteRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class, ExperimentalTime::class)
    @Test
    fun `invoke with valid ID should return the satellite detail`() = runTest {
        val satelliteId = 1
        val satelliteDetail = SatelliteDetailDomainModel(
            id = satelliteId,
            costPerLaunch = 1000000,
            firstFlight = "2022-01-01",
            height = 30,
            mass = 1000
        )
        `when`(satelliteRepository.getSatelliteDetail(satelliteId)).thenReturn(
            flowOf(
                satelliteDetail
            )
        )

        getDetail.invoke(satelliteId).test {
            assertEquals(Result.success(satelliteDetail.toUIModel()), awaitItem())
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class, ExperimentalTime::class)
    @Test
    fun `invoke with invalid ID should return failure`() = runTest {
        val satelliteId = -1
        `when`(satelliteRepository.getSatelliteDetail(satelliteId)).thenReturn(flowOf(null))
        getDetail.invoke(satelliteId).test {
            assertEquals(Result.failure<Throwable>(NullPointerException()), awaitItem())

        }
    }
}
