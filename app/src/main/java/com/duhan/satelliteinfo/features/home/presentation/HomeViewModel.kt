package com.duhan.satelliteinfo.features.home.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.duhan.satelliteinfo.features.base.presentation.FragmentUIEvent
import com.duhan.satelliteinfo.features.base.presentation.FragmentUIState
import com.duhan.satelliteinfo.features.base.presentation.FragmentViewModel
import com.duhan.satelliteinfo.features.home.domain.GetSatellites
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSatellites: GetSatellites
) : FragmentViewModel<HomeUIEvent, HomeUIState>() {
    fun onSatelliteItemClick(item: SatelliteItemModel) {

    }
}

sealed interface HomeUIState : FragmentUIState {
    object Loading : HomeUIState
    data class Error(
        @StringRes val messageId: Int = 0,
        @StringRes val detailId: Int = 0,
        @DrawableRes val iconId: Int = 0,
    ) : HomeUIState

    data class Success(
        var satelliteList: List<SatelliteItemModel>,
    ) : HomeUIState
}

sealed interface HomeUIEvent : FragmentUIEvent