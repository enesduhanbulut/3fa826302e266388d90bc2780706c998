package com.duhan.satelliteinfo.features.home.presentation

import app.cash.turbine.test
import com.duhan.satelliteinfo.MainDispatcherRule
import com.duhan.satelliteinfo.features.home.domain.GetFilteredSatellites
import com.duhan.satelliteinfo.features.home.domain.GetSatellites
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import kotlin.time.ExperimentalTime

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var getSatellites: GetSatellites
    private lateinit var getFilteredSatellites: GetFilteredSatellites

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        getSatellites = Mockito.mock(GetSatellites::class.java)
        getFilteredSatellites = Mockito.mock(GetFilteredSatellites::class.java)
        viewModel = HomeViewModel(getSatellites, getFilteredSatellites)
    }

    @OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
    @Test
    fun `getSatellites() called, state must loading`() = runTest {
        Mockito.`when`(getSatellites.invoke()).thenReturn(flowOf(Result.success(emptyList())))
        viewModel.getSatellites()
        viewModel.uiState.test {
            awaitItem()// initial state is null
            assertEquals(HomeUIState.Loading, awaitItem())
            assert(awaitItem() is HomeUIState.Success)
        }
    }

    @OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
    @Test
    fun `getFilteredSatellites() called with query, state must be filtered list`() = runTest {
        // Mock the filtered satellites response
        val filteredSatellites = listOf(
            SatelliteItemModel(1, "Satellite 1", true),
            SatelliteItemModel(2, "Satellite 2", false)
        )
        Mockito.`when`(getFilteredSatellites.invoke("Satellite"))
            .thenReturn(flowOf(filteredSatellites))

        // Call the getFilteredSatellites function
        viewModel.searchSatellites("Satellite")
        viewModel.uiState.test {
            awaitItem() // Initial state is null
            assertEquals(HomeUIState.Loading, awaitItem())
            val uiState = awaitItem()
            assertTrue(uiState is HomeUIState.Success)
            assertEquals(filteredSatellites, (uiState as HomeUIState.Success).satelliteList)
        }
    }
}
