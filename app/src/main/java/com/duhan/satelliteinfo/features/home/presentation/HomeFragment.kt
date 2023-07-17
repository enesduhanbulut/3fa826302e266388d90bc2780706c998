package com.duhan.satelliteinfo.features.home.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.duhan.satelliteinfo.R
import com.duhan.satelliteinfo.databinding.FragmentHomeBinding
import com.duhan.satelliteinfo.databinding.LayoutSatelliteItemBinding
import com.duhan.satelliteinfo.features.base.presentation.BaseFragment
import com.duhan.satelliteinfo.features.base.presentation.BaseListAdapter
import com.duhan.satelliteinfo.features.base.presentation.showBottomSheet
import com.duhan.satelliteinfo.features.core.presantation.LoadingBarInteractor
import com.duhan.satelliteinfo.features.core.presantation.LoadingBarInteractorImpl
import com.duhan.satelliteinfo.features.core.presantation.SpaceModel
import com.duhan.satelliteinfo.features.core.presantation.setup
import com.duhan.satelliteinfo.features.detail.presentation.DetailFragment
import com.duhan.satelliteinfo.features.detail.presentation.DetailUIEvent
import com.duhan.satelliteinfo.features.detail.presentation.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeUIEvent, HomeUIState, HomeViewModel>(),
    LoadingBarInteractor<HomeUIState> by LoadingBarInteractorImpl(),
    SearchView.OnQueryTextListener {
    override val viewModel: HomeViewModel by viewModels()
    private val detailViewModel: DetailViewModel by activityViewModels()
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
            spaceModel = SpaceModel(
                resources.getDimensionPixelSize(R.dimen.margin_8dp),
                resources.getDimensionPixelSize(R.dimen.margin_8dp)
            ),
        )
        registerLoadingBar(
            supportFragmentManager = parentFragmentManager,
            loadingState = HomeUIState.Loading,
            viewDataBinding = binding,
            loadingBarContainerId = R.id.loading_container
        )
        initMenu()
        viewModel.getSatellites()
    }

    private fun initMenu() {
        (requireActivity()).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
                val searchItem = menu.findItem(R.id.search)
                val searchView = searchItem.actionView as SearchView?
                searchView!!.setOnQueryTextListener(this@HomeFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    override fun setBindingViewModel() {
        mBinding?.viewModel = viewModel
    }

    override fun handleArgs(args: Bundle) {
    }

    override fun handleUIState(it: HomeUIState) {
        onStateChange(it)
    }

    override fun handleUIEvent(it: HomeUIEvent) {
        when (it) {
            is HomeUIEvent.NavigateToDetail -> {
                showBottomSheet(
                    DetailFragment(),
                    it.item.toBundle(),
                    detailViewModel,
                    {
                        handleBottomSheetEvent(it)
                    },
                    DetailUIEvent.Dismiss as DetailUIEvent
                )
            }

        }
    }

    private fun handleBottomSheetEvent(it: DetailUIEvent) {

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        performSearch(query)
        return true
    }

    private fun performSearch(query: String?) {
        lifecycleScope.launch {
            delay(200)
            viewModel.searchSatellites(query)
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) {
            viewModel.getSatellites()
            return true
        }
        if (newText.length > 2) {
            performSearch(newText)
            return true
        }
        return true

    }


}