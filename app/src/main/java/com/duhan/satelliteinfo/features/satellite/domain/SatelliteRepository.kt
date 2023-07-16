package com.duhan.satelliteinfo.features.satellite.domain

import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model.SatelliteListItem
import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDetailDomainModel
import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDomainModel
import com.duhan.satelliteinfo.features.satellite.domain.model.SatellitePositionDomainModel
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    fun getSatelliteList(): Flow<List<SatelliteDomainModel>?>
    fun getSatelliteDetail(id: Int): Flow<SatelliteDetailDomainModel?>
    fun getSatellitePosition(id: Int): Flow<SatellitePositionDomainModel?>

}
