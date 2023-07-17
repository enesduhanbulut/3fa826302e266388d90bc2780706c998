package com.duhan.satelliteinfo.features.home.domain

import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HomeDomainModule {
    @Provides
    fun provideGetSatellites(repository: SatelliteRepository) = GetSatellites(repository)

    @Provides
    fun provideGetFilteredSatellites(repository: SatelliteRepository) =
        GetFilteredSatellites(repository)
}