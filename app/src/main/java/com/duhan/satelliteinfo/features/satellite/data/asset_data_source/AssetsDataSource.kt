package com.duhan.satelliteinfo.features.satellite.data.asset_data_source

import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model.SatelliteDetailResponse
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model.SatelliteListResponse
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model.SatellitePositionsResponse

interface AssetsDataSource {
    fun getSatelliteList(): SatelliteListResponse?
    fun getSatelliteDetailList(): SatelliteDetailResponse?
    fun getPositions(): SatellitePositionsResponse?
}