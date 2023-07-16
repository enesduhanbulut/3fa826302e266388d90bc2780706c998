package com.duhan.satelliteinfo.features.satellite.data.asset_data_source

interface IAssetsDataSource {
    suspend fun getSatelliteList(): SatelliteListResponse
    suspend fun getSatelliteDetailList(): SatelliteDetailResponse
    suspend fun getPositionList(): SatellitePositionsResponse
}