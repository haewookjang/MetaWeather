package io.hwjang.app.weather.ui.local

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.hwjang.app.weather.R
import io.hwjang.app.weather.base.BaseDataBindingFragment

import io.hwjang.app.weather.databinding.FragmentLocalListBinding
import io.hwjang.app.weather.ui.local.adapter.LocationListAdapter
import io.hwjang.app.weather.widget.decoration.DividerDecoration
import javax.inject.Inject

@AndroidEntryPoint
class LocalListFragment :
    BaseDataBindingFragment<FragmentLocalListBinding, LocalListViewModel>(R.layout.fragment_local_list) {

    @Inject
    lateinit var locationListAdapter: LocationListAdapter

    override val viewModel: LocalListViewModel by viewModels()
    override fun initDataBinding() {
        binding.list.apply {
            adapter = locationListAdapter
            addItemDecoration(DividerDecoration(this.context))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.result.observe(this, Observer {
            locationListAdapter.submitList(it)
        })
        viewModel.load()
    }
}