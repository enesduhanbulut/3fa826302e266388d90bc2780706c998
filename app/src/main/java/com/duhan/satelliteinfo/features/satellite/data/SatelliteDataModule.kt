package com.duhan.satelliteinfo.features.satellite.data

import android.content.Context
import com.duhan.satelliteinfo.features.core.data.AppDatabase
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.AssetsDataSource
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.AssetsDataSourceImpl
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteDetailDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatelliteItemDao
import com.duhan.satelliteinfo.features.satellite.data.db_data_source.dao.SatellitePositionDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SatelliteDataModule {
    @Provides
    fun provideSatelliteItemDao(appDatabase: AppDatabase): SatelliteItemDao {
        return appDatabase.satelliteItemDao()
    }

    @Provides
    fun provideSatelliteDetailDao(appDatabase: AppDatabase): SatelliteDetailDao {
        return appDatabase.satelliteDetailDao()
    }

    @Provides
    fun provideSatellitePositionDao(appDatabase: AppDatabase): SatellitePositionDao {
        return appDatabase.satellitePositionDao()
    }

    @Provides
    fun provideAssetDataSource(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): AssetsDataSource {
        return AssetsDataSourceImpl(context, moshi)
    }
}