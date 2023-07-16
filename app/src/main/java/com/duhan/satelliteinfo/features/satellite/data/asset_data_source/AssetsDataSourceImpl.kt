package com.duhan.satelliteinfo.features.satellite.data.asset_data_source

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream

class AssetsDataSourceImpl(
    @ApplicationContext private val context: Context,
    private val moshi: Moshi
) : AssetsDataSource {
    override fun getSatelliteList(): SatelliteListResponse {
        return SatelliteListResponse().apply {
            addAll(readAssetAsModelList(LIST_FILE_NAME) ?: emptyList())
        }
    }

    override fun getSatelliteDetailList(): SatelliteDetailResponse {
        return SatelliteDetailResponse().apply {
            addAll(readAssetAsModelList(DETAIL_FILE_NAME) ?: emptyList())
        }
    }

    override fun getPositions(): SatellitePositionsResponse? {
        return readAssetAsModel(POSITION_FILE_NAME)
    }

    private inline fun <reified T> readAssetAsModelList(fileName: String): List<T>? {
        val type = Types.newParameterizedType(List::class.java, T::class.java)
        return moshi.adapter<List<T>>(type)
            .fromJson(assetToJson(context.assets.open(fileName))) as ArrayList<T>?
    }

    private inline fun <reified T> readAssetAsModel(fileName: String): T? {
        return moshi.adapter(T::class.java).fromJson(assetToJson(context.assets.open(fileName)))
    }

    private fun assetToJson(asset: InputStream): String {
        val buffer = ByteArray(asset.available())
        asset.read(buffer)
        asset.close()
        return String(buffer)
    }

    companion object {
        const val LIST_FILE_NAME = "satellite-list.json"
        const val DETAIL_FILE_NAME = "satellite-detail.json"
        const val POSITION_FILE_NAME = "positions.json"
    }
}