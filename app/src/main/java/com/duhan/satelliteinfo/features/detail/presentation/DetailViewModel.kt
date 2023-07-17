package com.duhan.satelliteinfo.features.detail.presentation

import androidx.lifecycle.viewModelScope
import com.duhan.satelliteinfo.R
import com.duhan.satelliteinfo.features.base.presentation.BottomSheetEvent
import com.duhan.satelliteinfo.features.base.presentation.BottomSheetState
import com.duhan.satelliteinfo.features.base.presentation.BottomSheetViewModel
import com.duhan.satelliteinfo.features.detail.domain.GetDetail
import com.duhan.satelliteinfo.features.detail.domain.GetPositions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetails: GetDetail,
    private val getPositions: GetPositions
) : BottomSheetViewModel<DetailUIEvent, DetailUIState>() {
    fun init(id: Int, name: String) {
        setState(DetailUIState.Loading)
        startGetDetails(id, name)
        startGetPositions(id)
    }

    private fun startGetDetails(id: Int, bundleName: String) {
        viewModelScope.launch {
            getDetails.invoke(id)
                .collectLatest {
                    if (it.isSuccess && it.getOrNull() != null) {
                        val satelliteDetailUIModel = it.getOrNull()!!
                        setState(DetailUIState.Success(satelliteDetailUIModel.apply {
                            name = bundleName
                        }))
                    } else {
                        setState(
                            DetailUIState.Error(
                                messageId = R.string.error,
                                detailId = R.string.can_not_get_satellite_detail,
                                iconId = R.drawable.not_found
                            )
                        )
                    }
                }
        }

    }

    @OptIn(FlowPreview::class)
    private fun startGetPositions(id: Int) {
        viewModelScope.launch {
            getPositions.invoke(id)
                .collect {
                    updatePosition(it)
                }
        }

    }

    private fun updatePosition(it: String) {
        setState(
            withStateValue { state ->
                if (state is DetailUIState.Success) {
                    DetailUIState.Success(state.satelliteDetailUIModel.apply {
                        position = it
                    })
                } else {
                    state
                }
            }
        )
    }

    fun onDestroy() {
        setEvent(DetailUIEvent.Dismiss)
    }
}

sealed interface DetailUIEvent : BottomSheetEvent {
    object Dismiss : DetailUIEvent

}

sealed interface DetailUIState : BottomSheetState {
    object Loading : DetailUIState
    data class Success(val satelliteDetailUIModel: SatelliteDetailUIModel) : DetailUIState
    data class Error(
        val messageId: Int,
        val detailId: Int,
        val iconId: Int
    ) : DetailUIState
}
