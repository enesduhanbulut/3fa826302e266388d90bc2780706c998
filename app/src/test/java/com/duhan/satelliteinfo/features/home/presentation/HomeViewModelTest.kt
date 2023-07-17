package com.duhan.satelliteinfo.features.home.presentation

import app.cash.turbine.test
import com.duhan.satelliteinfo.MainDispatcherRule
import com.duhan.satelliteinfo.features.home.domain.GetSatellites
import junit.framework.Assert.assertEquals
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        getSatellites = Mockito.mock(GetSatellites::class.java)
        viewModel = HomeViewModel(getSatellites)
    }

    @OptIn(ExperimentalTime::class)
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
}