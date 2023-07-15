package com.duhan.satelliteinfo.features.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<
        DB : ViewDataBinding,
        UE : FragmentUIEvent,
        US : FragmentUIState,
        VM : FragmentViewModel<UE, US>,
        > : Fragment(), FragmentInitializer<DB, UE, US, VM> {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return onOnCreateViewTask(viewLifecycleOwner, savedInstanceState, inflater, container)
    }

    override fun onDestroyView() {
        onDestroyViewTask()
        super.onDestroyView()
    }
}
