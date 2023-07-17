package com.duhan.satelliteinfo.features.satellite.domain

import com.duhan.satelliteinfo.features.base.domain.RefreshControl
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.AssetsDataSource
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteDetailDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteItemDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatellitePositionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object SatelliteDomainModule {
    @Provides
    fun provideSatelliteRepository(
        satelliteItemDao: SatelliteItemDao,
        satelliteDetailDao: SatelliteDetailDao,
        satellitePositionDao: SatellitePositionDao,
        assetsDataSource: AssetsDataSource,
    ): SatelliteRepository {
        return SatelliteRepositoryImpl(
            Dispatchers.IO,
            satellitePositionDao,
            satelliteItemDao,
            satelliteDetailDao,
            assetsDataSource,
            RefreshControl.ONE_TIME
        )
    }
}