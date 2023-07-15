package com.duhan.satelliteinfo.features.home.presentation

import com.duhan.satelliteinfo.features.base.presentation.FragmentUIEvent
import com.duhan.satelliteinfo.features.base.presentation.FragmentUIState
import com.duhan.satelliteinfo.features.base.presentation.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : FragmentViewModel<HomeUIEvent, HomeUIState>() {
}

sealed interface HomeUIState : FragmentUIState {
    object Loading : HomeUIState
}

sealed interface HomeUIEvent : FragmentUIEvent