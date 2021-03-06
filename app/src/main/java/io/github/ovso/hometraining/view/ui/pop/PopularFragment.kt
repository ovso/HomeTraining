package io.github.ovso.hometraining.view.ui.pop

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import io.github.ovso.hometraining.R
import io.github.ovso.hometraining.databinding.FragmentPopularBinding
import io.github.ovso.hometraining.view.base.DataBindingFragment2
import io.github.ovso.hometraining.view.ui.video.VideoAdapter
import kotlinx.android.synthetic.main.fragment_popular.*
import org.koin.android.ext.android.inject

class PopularFragment : DataBindingFragment2<FragmentPopularBinding>(
    layoutResId = R.layout.fragment_popular,
    viewModelCls = PopularViewModel::class.java
) {

    private val adapter: VideoAdapter by inject()
    private val itemDivider: DividerItemDecoration by inject()

    companion object {
        val TAG: String = PopularFragment::class.java.simpleName
        fun newInstance() = PopularFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRv()
        observe()
    }

    private fun setupRv() {
        with(rv_popular) {
            addItemDecoration(itemDivider)
            adapter = this@PopularFragment.adapter
        }
        rv_popular.adapter = adapter
    }

    private fun observe() {
        val owner = viewLifecycleOwner
        binding.viewModel?.items?.observe(owner, Observer {
            adapter.submitList(it)
        })
    }
}
