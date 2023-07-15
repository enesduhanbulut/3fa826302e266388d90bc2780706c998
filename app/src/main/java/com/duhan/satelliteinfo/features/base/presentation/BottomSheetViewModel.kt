package com.duhan.satelliteinfo.features.base.presentation

abstract class BottomSheetViewModel<UE : BottomSheetEvent, US : BottomSheetState> :
    BaseViewModel<UE, US>()

interface BottomSheetEvent : BaseUIEvent
interface BottomSheetState : BaseUIState
