package com.duhan.satelliteinfo.features.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Job

abstract class BaseFragment<
        DB : ViewDataBinding,
        UE : FragmentUIEvent,
        US : FragmentUIState,
        VM : FragmentViewModel<UE, US>,
        > : Fragment(), FragmentInitializer<DB, UE, US, VM> {
    override var observeJobs: MutableList<Job> = mutableListOf()
    override var mBinding: DB? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = onOnCreateViewTask(viewLifecycleOwner, savedInstanceState, inflater, container)
        return mBinding!!.root
    }

    override fun onDestroyView() {
        onDestroyViewTask()
        super.onDestroyView()
    }
}
