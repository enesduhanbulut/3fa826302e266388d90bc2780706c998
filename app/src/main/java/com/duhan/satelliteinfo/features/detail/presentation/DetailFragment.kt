package com.duhan.satelliteinfo.features.detail.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.duhan.satelliteinfo.R
import com.duhan.satelliteinfo.databinding.FragmentDetailBinding
import com.duhan.satelliteinfo.features.base.presentation.BaseBottomSheet

class DetailFragment : BaseBottomSheet<FragmentDetailBinding,
        DetailUIEvent, DetailUIState, DetailViewModel>() {
    override val layoutId = R.layout.fragment_detail
    override val titleId = R.string.detail_fragment_title
    override val viewModel: DetailViewModel by viewModels()
    override val fragmentTag = "DetailFragment"

    override fun setBindingViewModel() {
        mBinding?.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
        val detailFragmentArgs = DetailFragmentArgs.fromBundle(args)
        viewModel.init(detailFragmentArgs)
    }

    override fun handleUIState(it: DetailUIState) {
        TODO("Not yet implemented")
    }

    override fun handleUIEvent(it: DetailUIEvent) {
        TODO("Not yet implemented")
    }

    override fun initView(binding: FragmentDetailBinding) {
        TODO("Not yet implemented")
    }

}