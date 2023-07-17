package com.duhan.satelliteinfo.features.detail.presentation

data class SatelliteDetailUIModel(
    val id: Int,
    val name: String,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int,
    var position: Pair<Double, Double>,
)