package com.duhan.satelliteinfo.features.detail.domain

import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DetailDomainModule {
    @Provides
    fun provideGetDetail(satelliteRepository: SatelliteRepository) = GetDetail(satelliteRepository)

    @Provides
    fun provideGetPositions(satelliteRepository: SatelliteRepository) =
        GetPositions(satelliteRepository)
}