package com.duhan.satelliteinfo.features.home.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.duhan.satelliteinfo.R
import com.duhan.satelliteinfo.databinding.FragmentHomeBinding
import com.duhan.satelliteinfo.databinding.LayoutSatelliteItemBinding
import com.duhan.satelliteinfo.features.base.presentation.BaseFragment
import com.duhan.satelliteinfo.features.base.presentation.BaseListAdapter
import com.duhan.satelliteinfo.features.core.presantation.setup

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeUIEvent, HomeUIState, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_home
    override val titleId: Int = R.string.home_fragment_title
    override val fragmentTag: String = "HomeFragment"
    private lateinit var satelliteAdapter: BaseListAdapter<SatelliteItemModel, LayoutSatelliteItemBinding>

    override fun initView(binding: FragmentHomeBinding) {
        satelliteAdapter = object : BaseListAdapter<SatelliteItemModel, LayoutSatelliteItemBinding>(
            itemClickListener = { item, _ ->
                viewModel.onSatelliteItemClick(item)
            },
        ) {
            override val layoutId = R.layout.layout_satellite_item
            override fun setUIState(binding: LayoutSatelliteItemBinding, item: SatelliteItemModel) {
                binding.uiModel = item
            }
        }
        binding.satellites.setup(
            adapter = satelliteAdapter,
        )
    }

    override fun setBindingViewModel() {
        mBinding?.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
    }

    override fun handleUIState(it: HomeUIState) {
    }

    override fun handleUIEvent(it: HomeUIEvent) {
    }


}