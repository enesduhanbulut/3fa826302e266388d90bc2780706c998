import app.cash.turbine.test
import com.duhan.satelliteinfo.features.home.domain.GetFilteredSatellites
import com.duhan.satelliteinfo.features.home.presentation.SatelliteItemModel
import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDomainModel
import com.duhan.satelliteinfo.features.satellite.domain.model.toUIModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class GetFilteredSatellitesTest {

    @Mock
    private lateinit var satelliteRepository: SatelliteRepository

    private lateinit var getFilteredSatellites: GetFilteredSatellites

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getFilteredSatellites = GetFilteredSatellites(satelliteRepository)
    }

    @Test
    fun `invoke with non-null query should return filtered satellite list`() = runTest {
        val satelliteList = listOf(
            SatelliteDomainModel(1, "Satellite 1", true),
            SatelliteDomainModel(2, "Satellite 2", false),
            SatelliteDomainModel(3, "Satellite 3", true)
        )
        `when`(satelliteRepository.getSatelliteList()).thenReturn(flowOf(satelliteList))
        val query = "Satellite 2"
        val filteredSatellites = getFilteredSatellites(query)

        val result = mutableListOf<SatelliteItemModel>()
        filteredSatellites.collect { result.addAll(it) }
        assertEquals(listOf(SatelliteItemModel(2, "Satellite 2", false)), result)
    }

    @Test
    fun `invoke with null query should return unfiltered satellite list`() = runTest {
        val satelliteList = listOf(
            SatelliteDomainModel(1, "Satellite 1", true),
            SatelliteDomainModel(2, "Satellite 2", false),
            SatelliteDomainModel(3, "Satellite 3", true)
        )
        `when`(satelliteRepository.getSatelliteList()).thenReturn(flowOf(satelliteList))
        val filteredSatellites = getFilteredSatellites.invoke(null)

        val result = mutableListOf<SatelliteItemModel>()
        filteredSatellites.collect { result.addAll(it) }
        assertEquals(satelliteList.map { it.toUIModel() }, result)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `invoke with empty query should return unfiltered satellite list`() = runTest {
        val satelliteList = listOf(
            SatelliteDomainModel(1, "Satellite 1", true),
            SatelliteDomainModel(2, "Satellite 2", false),
            SatelliteDomainModel(3, "Satellite 3", true)
        )
        `when`(satelliteRepository.getSatelliteList()).thenReturn(flowOf(satelliteList))
        getFilteredSatellites.invoke("").test {
            assertEquals(satelliteList.map { it.toUIModel() }, awaitItem())
            awaitComplete()
        }

    }

    @Test
    fun `invoke with query not matching any satellite should return empty list`() = runTest {
        val satelliteList = listOf(
            SatelliteDomainModel(1, "Satellite 1", true),
            SatelliteDomainModel(2, "Satellite 2", false),
            SatelliteDomainModel(3, "Satellite 3", true)
        )
        `when`(satelliteRepository.getSatelliteList()).thenReturn(flowOf(satelliteList))
        val filteredSatellites = getFilteredSatellites("Unknown")

        val result = mutableListOf<SatelliteItemModel>()
        filteredSatellites.collect { result.addAll(it) }
        assertEquals(emptyList<SatelliteItemModel>(), result)
    }
}
