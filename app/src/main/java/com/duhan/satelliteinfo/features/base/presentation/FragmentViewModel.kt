package com.duhan.satelliteinfo.features.base.presentation

abstract class FragmentViewModel<UE : FragmentUIEvent, US : FragmentUIState> :
    BaseViewModel<UE, US>()

interface FragmentUIState : BaseUIState

interface FragmentUIEvent : BaseUIEvent
