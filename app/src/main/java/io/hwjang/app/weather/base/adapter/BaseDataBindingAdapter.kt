package io.hwjang.app.weather.base.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseDataBindingAdapter<T, DB : ViewDataBinding>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, DataBindingViewHolder<T, DB>>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder<T, DB> {
        val binding = createDataBinding(parent, viewType)
        initViewDataBinding(binding)
        return createDataBindingViewHolder(binding)
    }

    abstract fun createDataBinding(
        parent: ViewGroup,
        viewType: Int
    ): DB

    abstract fun createDataBindingViewHolder(binding: DB): DataBindingViewHolder<T, DB>

    open fun initViewDataBinding(binding: ViewDataBinding) {

    }

    override fun getItemViewType(position: Int): Int {
        return getDataBindingLayout(position)
    }

    abstract fun getDataBindingLayout(position: Int): Int

    override fun onBindViewHolder(holder: DataBindingViewHolder<T, DB>, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }


}