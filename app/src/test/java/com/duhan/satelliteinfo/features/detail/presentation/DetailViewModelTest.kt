import app.cash.turbine.test
import com.duhan.satelliteinfo.MainDispatcherRule
import com.duhan.satelliteinfo.features.detail.domain.GetDetail
import com.duhan.satelliteinfo.features.detail.domain.GetPositions
import com.duhan.satelliteinfo.features.detail.presentation.DetailUIState
import com.duhan.satelliteinfo.features.detail.presentation.DetailViewModel
import com.duhan.satelliteinfo.features.detail.presentation.SatelliteDetailUIModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @Mock
    private lateinit var getDetails: GetDetail

    @Mock
    private lateinit var getPositions: GetPositions

    private lateinit var viewModel: DetailViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailViewModel(getDetails, getPositions)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `init should start getDetails and getPositions`() = runTest {
        val id = 1
        val name = "Satellite"

        `when`(getDetails.invoke(id)).thenReturn(flowOf(Result.success(createSatelliteDetailUIModel())))
        `when`(getPositions.invoke(id)).thenReturn(flowOf("1.0, 2.0", "3.0, 4.0"))

        viewModel.init(id, name)

        var uiModel: SatelliteDetailUIModel? = null
        viewModel.uiState.test(Duration.ZERO) {
            uiModel = createSatelliteDetailUIModel()
            assertEquals(null, awaitItem())
            assertEquals(DetailUIState.Loading, awaitItem())
            assertEquals(DetailUIState.Success(createSatelliteDetailUIModel()), awaitItem()
                .apply {
                    uiModel = (this as DetailUIState.Success).satelliteDetailUIModel
                })
            assertEquals("", uiModel?.position)
        }
    }

    private fun createSatelliteDetailUIModel(): SatelliteDetailUIModel {
        return SatelliteDetailUIModel(
            id = 1,
            name = "Satellite",
            costPerLaunch = "1000",
            firstFlight = "2022-01-01",
            heightMass = "1000/100000",
            "",
        )
    }
}
