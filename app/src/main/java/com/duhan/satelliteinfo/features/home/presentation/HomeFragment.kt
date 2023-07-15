package com.duhan.satelliteinfo.features.home.presentation

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.duhan.satelliteinfo.R
import com.duhan.satelliteinfo.databinding.FragmentHomeBinding
import com.duhan.satelliteinfo.features.base.presentation.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeUIEvent, HomeUIState, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_home
    override val titleId: Int = R.string.home_fragment_title
    override val fragmentTag: String = "HomeFragment"


    override fun setBindingViewModel() {

    }

    override fun handleArgs(args: Bundle) {
    }

    override fun handleUIState(it: HomeUIState) {
    }

    override fun handleUIEvent(it: HomeUIEvent) {
    }

    override fun initView(binding: FragmentHomeBinding) {
    }


}