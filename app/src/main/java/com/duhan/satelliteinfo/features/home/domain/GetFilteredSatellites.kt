package com.duhan.satelliteinfo.features.home.domain

import com.duhan.satelliteinfo.features.home.presentation.SatelliteItemModel
import com.duhan.satelliteinfo.features.satellite.domain.SatelliteRepository
import com.duhan.satelliteinfo.features.satellite.domain.model.toUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip

class GetFilteredSatellites(
    private val repository: SatelliteRepository
) {
    operator fun invoke(query: String?): Flow<List<SatelliteItemModel>> {
        var searchQuery = query ?: ""
        val response = repository.getSatelliteList()
            .map { domainModels -> domainModels?.map { it.toUIModel() } }
            .map { Result.success(it) }
            .catch { emit(Result.failure(it)) }
            .filter { it.isSuccess }
            .map { it.getOrNull() ?: emptyList() }

        return response.map { satelliteItemModels ->
            satelliteItemModels.filter {
                (if (it.active) "Active" else "Passive").lowercase()
                    .contains(searchQuery.lowercase(), false)
            }
        }.zip(response.map { satelliteItemModels ->
            satelliteItemModels.filter {
                it.name.lowercase().contains(searchQuery.lowercase(), false)
            }
        }) { satelliteItemModels, satelliteItemModels2 ->
            satelliteItemModels + satelliteItemModels2
        }.map { it.distinct() }
    }
}