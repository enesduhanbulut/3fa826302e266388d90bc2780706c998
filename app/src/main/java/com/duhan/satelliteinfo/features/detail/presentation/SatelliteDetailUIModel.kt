package com.duhan.satelliteinfo.features.detail.presentation

data class SatelliteDetailUIModel(
    val id: Int,
    var name: String,
    val costPerLaunch: String,
    val firstFlight: String,
    val heightMass: String,
    var position: String,
)