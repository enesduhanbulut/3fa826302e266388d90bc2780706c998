package com.duhan.satelliteinfo.features.detail.presentation

import com.duhan.satelliteinfo.features.base.presentation.FragmentUIEvent
import com.duhan.satelliteinfo.features.base.presentation.FragmentUIState
import com.duhan.satelliteinfo.features.base.presentation.FragmentViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : FragmentViewModel<DetailUIEvent, DetailUIState>()

sealed interface DetailUIEvent : FragmentUIEvent
sealed interface DetailUIState : FragmentUIState {
    object Loading : DetailUIState
    data class Success(val detailUIModel: DetailUIModel) : DetailUIState
    data class Error(
        val messageId: Int,
        val detailId: Int,
        val iconId: Int
    ) : DetailUIState
}
