package com.duhan.satelliteinfo.features.home.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.duhan.satelliteinfo.R
import com.duhan.satelliteinfo.features.base.presentation.FragmentUIEvent
import com.duhan.satelliteinfo.features.base.presentation.FragmentUIState
import com.duhan.satelliteinfo.features.base.presentation.FragmentViewModel
import com.duhan.satelliteinfo.features.home.domain.GetSatellites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSatellites: GetSatellites
) : FragmentViewModel<HomeUIEvent, HomeUIState>() {

    fun getSatellites() {
        setState(HomeUIState.Loading)
        viewModelScope.launch {
            getSatellites.invoke()
                .collectLatest {
                    if (it.isSuccess) {
                        setState(
                            HomeUIState.Success(satelliteList = it.getOrNull() ?: emptyList())
                        )
                    } else {
                        setState(
                            HomeUIState.Error(
                                messageId = R.string.error,
                                detailId = R.string.can_not_get_satellite_list,
                                iconId = R.drawable.not_found
                            )
                        )
                    }
                }
        }
    }

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