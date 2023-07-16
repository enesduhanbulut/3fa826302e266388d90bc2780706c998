package com.duhan.satelliteinfo.features.satellite.data.asset_data_source

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class AssetsDataSourceImplTest {
    private lateinit var assetsDataSource: AssetsDataSource

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        assetsDataSource = AssetsDataSourceImpl(
            context, Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )
    }

    @Test
    fun getSatelliteList() {
        val satelliteList = assetsDataSource.getSatelliteList()
        assert(satelliteList?.isNotEmpty() == true)
    }

    @Test
    fun getSatelliteDetailList() {
        val satelliteDetailList = assetsDataSource.getSatelliteDetailList()
        assert(satelliteDetailList?.isNotEmpty() == true)
    }

    @Test
    fun getPositions() {
        val positions = assetsDataSource.getPositions()
        assert(positions?.positions?.isNotEmpty() == true)
    }
}