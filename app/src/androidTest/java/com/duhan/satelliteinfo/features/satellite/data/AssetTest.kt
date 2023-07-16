package com.duhan.satelliteinfo.features.satellite.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.SatelliteDetailItem
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.SatelliteListItem
import com.duhan.satelliteinfo.features.satellite.data.asset_data_source.SatellitePositionsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.hamcrest.Matchers
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStream

@RunWith(AndroidJUnit4::class)
class AssetTest {
    private lateinit var assetPositionList: InputStream
    private lateinit var assetDetailList: InputStream
    private lateinit var assetList: InputStream
    private val moshi = Moshi.Builder()
        .add(
            KotlinJsonAdapterFactory()
        ).build()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val assetManager = context.assets
        assetList = assetManager.open("satellite-list.json")
        assetDetailList = assetManager.open("satellite-detail.json")
        assetPositionList = assetManager.open("positions.json")
    }

    @Test
    fun test_if_assets_is_exist_and_not_empty() {
        assertThat(assetList.available(), Matchers.greaterThan(0))
        assertThat(assetDetailList.available(), Matchers.greaterThan(0))
        assertThat(assetPositionList.available(), Matchers.greaterThan(0))
    }

    @Test
    fun test_if_assets_is_valid_json() {
        val assetListJson = assetToJsonArray(assetList)
        val assetDetailListJson = assetToJsonArray(assetDetailList)
        val assetPositionListJson = assetToJson(assetPositionList)

        assertThat(assetListJson.length(), Matchers.greaterThan(0))
        assertThat(assetDetailListJson.length(), Matchers.greaterThan(0))
        assertThat(assetPositionListJson.has("list"), Matchers.equalTo(true))
    }

    @Test
    fun test_if_assets_json_can_deserialize() {
        val assetListJson = assetToJsonArray(assetList)
        val assetDetailListJson = assetToJsonArray(assetDetailList)
        val assetPositionListJson = assetToJson(assetPositionList)
        val listType = Types.newParameterizedType(List::class.java, SatelliteListItem::class.java)
        val detailType =
            Types.newParameterizedType(List::class.java, SatelliteDetailItem::class.java)

        val assetList =
            moshi.adapter<List<SatelliteListItem>>(listType).fromJson(assetListJson.toString())
        val assetDetailList = moshi.adapter<List<SatelliteDetailItem>>(detailType)
            .fromJson(assetDetailListJson.toString())
        val assetPositionList = moshi.adapter(SatellitePositionsResponse::class.java)
            .fromJson(assetPositionListJson.toString())

        assertThat(assetList?.size!!, Matchers.greaterThan(0))
        assertThat(assetDetailList?.size!!, Matchers.greaterThan(0))
        assertThat(assetPositionList?.positions, Matchers.notNullValue())
        assertThat(assetPositionList?.positions?.size!!, Matchers.greaterThan(0))
    }

    private fun assetToJson(asset: InputStream): JSONObject {
        val buffer = ByteArray(asset.available())
        asset.read(buffer)
        asset.close()
        return JSONObject(String(buffer))
    }

    private fun assetToJsonArray(asset: InputStream): JSONArray {
        val buffer = ByteArray(asset.available())
        asset.read(buffer)
        asset.close()
        return JSONArray(String(buffer))
    }
}