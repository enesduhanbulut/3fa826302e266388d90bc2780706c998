package com.duhan.satelliteinfo.features.satellite.data.asset_data_source

interface AssetsDataSource {
    fun getSatelliteList(): SatelliteListResponse?
    fun getSatelliteDetailList(): SatelliteDetailResponse?
    fun getPositions(): SatellitePositionsResponse?
}