package com.duhan.satelliteinfo.features.satellite.domain

import com.duhan.satelliteinfo.features.base.data.ResourceGroup
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.AssetsDataSource
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.model.toDomainModel
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteDetailDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteItemDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatellitePositionDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.model.toDomainModel
import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDetailDomainModel
import com.duhan.satelliteinfo.features.satellite.domain.model.SatelliteDomainModel
import com.duhan.satelliteinfo.features.satellite.domain.model.SatellitePositionDomainModel
import com.duhan.satelliteinfo.features.satellite.domain.model.toEntity
import kotlinx.coroutines.flow.Flow

class SatelliteRepositoryImpl(
    private val satellitePositionDao: SatellitePositionDao,
    private val satelliteItemDao: SatelliteItemDao,
    private val satelliteDetailDao: SatelliteDetailDao,
    private val assetsDataSource: AssetsDataSource
) : SatelliteRepository {
    private val satelliteResource = ResourceGroup<Unit, Int, SatelliteDomainModel>(
        { assetsDataSource.getSatelliteList()?.map { it.toDomainModel() } },
        { satelliteItemDao.getSatelliteList().map { it.toDomainModel() } },
        { list -> satelliteItemDao.insertAll(list.map { it.toEntity() }) },
        { id, _ -> assetsDataSource.getSatelliteById(id)?.toDomainModel() },
        { id, _ -> satelliteItemDao.getSatelliteItem(id).toDomainModel() },
        { item -> satelliteItemDao.insert(item.toEntity()) },
        { satelliteItemDao.deleteAll() }
    )
    private val detailResource = ResourceGroup<Unit, Int, SatelliteDetailDomainModel>(
        { assetsDataSource.getSatelliteDetailList()?.map { it.toDomainModel() } },
        { satelliteDetailDao.getSatelliteDetailList().map { it.toDomainModel() } },
        { list -> satelliteDetailDao.insertAll(list.map { it.toEntity() }) },
        { id, _ -> assetsDataSource.getSatelliteDetailById(id)?.toDomainModel() },
        { id, _ -> satelliteDetailDao.getSatelliteDetail(id).toDomainModel() },
        { item -> satelliteDetailDao.insert(item.toEntity()) },
        { satelliteDetailDao.deleteAll() }
    )

    private val positionResource = ResourceGroup<Unit, Int, SatellitePositionDomainModel>(
        { assetsDataSource.getPositions()?.positions?.map { it.toDomainModel() } },
        { satellitePositionDao.getSatellitePositionList().map { it.toDomainModel() } },
        { list -> satellitePositionDao.insertAll(list.map { it.toEntity() }) },
        { id, _ -> assetsDataSource.getPositionById(id)?.toDomainModel() },
        { id, _ -> satellitePositionDao.getSatellitePosition(id).toDomainModel() },
        { item -> satellitePositionDao.insert(item.toEntity()) },
        { satellitePositionDao.deleteAll() }
    )

    override fun getSatelliteList(): Flow<List<SatelliteDomainModel>?> {
        return satelliteResource.query(Unit)
    }

    override fun getSatelliteDetail(id: Int): Flow<SatelliteDetailDomainModel?> {
        return detailResource.queryByKey(id, Unit)
    }

    override fun getSatellitePosition(id: Int): Flow<SatellitePositionDomainModel?> {
        return positionResource.queryByKey(id, Unit)
    }


}