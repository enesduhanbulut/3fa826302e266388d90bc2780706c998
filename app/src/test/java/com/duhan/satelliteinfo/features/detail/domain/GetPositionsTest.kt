import app.cash.turbine.test
import com.duhan.satelliteinfo.features.detail.domain.GetPositions
import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import com.duhan.satelliteinfo.features.satellite.domain.model.SatellitePositionDomainModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class GetPositionsTest {

    @Mock
    private lateinit var satelliteRepository: SatelliteRepository

    private lateinit var getPositions: GetPositions

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getPositions = GetPositions(satelliteRepository)
    }

    @Test
    fun `invoke should emit positions with delay`() = runTest {
        val positions = listOf(
            Pair(1.0, 2.0),
            Pair(3.0, 4.0),
            Pair(5.0, 6.0)
        )
        val id = 1
        val delay = 3000L
        val EXECUTE_DELAY = 20L

        `when`(satelliteRepository.getSatellitePosition(id)).thenReturn(
            flowOf(
                SatellitePositionDomainModel(id, positions)
            )
        )

        getPositions.invoke(id, delay).test(Duration.ZERO) {
            val before = System.currentTimeMillis()
            assertEquals("1.0, 2.0", awaitItem())
            assert(System.currentTimeMillis() - before in delay - EXECUTE_DELAY..delay + EXECUTE_DELAY)
            assertEquals("3.0, 4.0", awaitItem())
            assert(System.currentTimeMillis() - before in delay * 2 - EXECUTE_DELAY..delay * 2 + EXECUTE_DELAY)
            assertEquals("5.0, 6.0", awaitItem())
            assert(System.currentTimeMillis() - before in delay * 3 - EXECUTE_DELAY..delay * 3 + EXECUTE_DELAY)
            awaitComplete()
        }
    }

    @Test
    fun `invoke should not emit positions for empty list`() = runTest {
        val positions = emptyList<Pair<Double, Double>>()
        val id = 1
        val delay = 3000L
        `when`(satelliteRepository.getSatellitePosition(id)).thenReturn(
            flowOf(
                SatellitePositionDomainModel(
                    id,
                    positions
                )
            )
        )
        val result = getPositions.invoke(id, delay)
            .toList()

        assertTrue(result.isEmpty())
    }
}
