package com.duhan.satelliteinfo.features.detail.presentation

import com.duhan.satelliteinfo.features.base.presentation.BottomSheetEvent
import com.duhan.satelliteinfo.features.base.presentation.BottomSheetState
import com.duhan.satelliteinfo.features.base.presentation.BottomSheetViewModel
import com.duhan.satelliteinfo.features.detail.domain.GetDetail
import com.duhan.satelliteinfo.features.detail.domain.GetPositions
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getDetails: GetDetail,
    private val getPositions: GetPositions
) : BottomSheetViewModel<DetailUIEvent, DetailUIState>() {
    fun init(detailFragmentArgs: DetailFragmentArgs) {

    }
}

sealed interface DetailUIEvent : BottomSheetEvent {
    object Dismiss : DetailUIState

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
