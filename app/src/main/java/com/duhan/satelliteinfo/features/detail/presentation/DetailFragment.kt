package com.duhan.satelliteinfo.features.detail.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.duhan.satelliteinfo.R
import com.duhan.satelliteinfo.databinding.FragmentDetailBinding
import com.duhan.satelliteinfo.features.base.presentation.BaseBottomSheet
import com.duhan.satelliteinfo.features.core.presantation.LoadingBarInteractor
import com.duhan.satelliteinfo.features.core.presantation.LoadingBarInteractorImpl
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseBottomSheet<FragmentDetailBinding,
        DetailUIEvent, DetailUIState, DetailViewModel>(),
    LoadingBarInteractor<DetailUIState> by LoadingBarInteractorImpl() {
    override val layoutId = R.layout.fragment_detail
    override val titleId = R.string.detail_fragment_title
    override val viewModel: DetailViewModel by viewModels()
    override val fragmentTag = "DetailFragment"

    override fun setBindingViewModel() {
        mBinding?.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
        val detailFragmentArgs = DetailFragmentArgs.fromBundle(args)
        viewModel.init(detailFragmentArgs.id, detailFragmentArgs.name)
    }

    override fun handleUIState(it: DetailUIState) {
        onStateChange(it)
        if (it is DetailUIState.Error) {
            Snackbar.make(
                requireActivity().findViewById(R.id.nav_host_fragment_container),
                getString(it.messageId) + " Message:" + getString(it.detailId),
                Snackbar.LENGTH_SHORT
            ).show()
            dismiss()
        }
    }

    override fun handleUIEvent(it: DetailUIEvent) {
    }

    override fun initView(binding: FragmentDetailBinding) {
        registerLoadingBar(
            supportFragmentManager = parentFragmentManager,
            loadingState = DetailUIState.Loading,
            viewDataBinding = binding,
            loadingBarContainerId = R.id.loading_container
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

}